include pxcore.inc

#DEPENDS += " python-native"
DEPENDS += " qemu-native "

COMPATIBLE_MACHINE_armv4 = "(!.*armv4).*"
COMPATIBLE_MACHINE_armv5 = "(!.*armv5).*"
COMPATIBLE_MACHINE_mips64 = "(!.*mips64).*"

inherit cmake pythonnative

# v8 errors out if you have set CCACHE
CCACHE = ""

def map_nodejs_arch(a, d):
    import re

    if   re.match('i.86$', a): return 'ia32'
    elif re.match('x86_64$', a): return 'x64'
    elif re.match('aarch64$', a): return 'arm64'
    elif re.match('powerpc64$', a): return 'ppc64'
    elif re.match('powerpc$', a): return 'ppc'
    return a

ARCHFLAGS_arm = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', '--with-arm-float-abi=hard', '--with-arm-float-abi=softfp', d)} \
                 ${@bb.utils.contains('TUNE_FEATURES', 'neon', '--with-arm-fpu=neon', \
                    bb.utils.contains('TUNE_FEATURES', 'vfpv3d16', '--with-arm-fpu=vfpv3-d16', \
                    bb.utils.contains('TUNE_FEATURES', 'vfpv3', '--with-arm-fpu=vfpv3', \
                    '--with-arm-fpu=vfp', d), d), d)}"
GYP_DEFINES_append_mipsel = " mips_arch_variant='r1' "
ARCHFLAGS ?= ""

# V8's JIT infrastructure requires binaries such as mksnapshot and
# mkpeephole to be run in the host during the build. However, these
# binaries must have the same bit-width as the target (e.g. a x86_64
# host targeting ARMv6 needs to produce a 32-bit binary). Instead of
# depending on a third Yocto toolchain, we just build those binaries
# for the target and run them on the host with QEMU.
python do_create_v8_qemu_wrapper () {
    """Creates a small wrapper that invokes QEMU to run some target V8 binaries
    on the host."""

    qemu_binary=qemu_target_binary(d)
    if qemu_binary == "qemu-allarch":
        qemu_binary = "qemuwrapper"

    qemu_options = d.getVar("QEMU_OPTIONS", True)

    rootfs_path=d.getVar('STAGING_DIR_HOST', True)
    libdir_qemu=d.expand('${STAGING_DIR_HOST}${libdir}')
    base_libdir_qemu=d.expand('${STAGING_DIR_HOST}${base_libdir}')

    qemu_cmd= qemu_binary + " " + qemu_options + " -L " + rootfs_path \
            + " -E LD_LIBRARY_PATH=" + libdir_qemu + ":"+ base_libdir_qemu + " "

    wrapper_path = d.expand('${S}/examples/pxScene2d/external/v8-qemu-wrapper.sh')
    with open(wrapper_path, 'w') as wrapper_file:
        wrapper_file.write("""#!/bin/sh

# This file has been generated automatically.
# It invokes QEMU to run binaries built for the target in the host during the
# build process.
%s "$@"
""" % qemu_cmd)
    os.chmod(wrapper_path, 0o755)
}

inherit qemu
do_create_v8_qemu_wrapper[dirs] = "${B}"
addtask create_v8_qemu_wrapper after do_patch before do_configure

# Node is way too cool to use proper autotools, so we install two wrappers to forcefully inject proper arch cflags to workaround gypi
do_configure () {
    rm -rf ${S}/deps/openssl
    cd ${S}/examples/pxScene2d/external/libnode-v${PV}
    export LD="${CXX}"
    GYP_DEFINES="${GYP_DEFINES}" export GYP_DEFINES
    # $TARGET_ARCH settings don't match --dest-cpu setting
   ./configure --prefix=${prefix} --without-snapshot --shared-openssl --without-intl --shared \
               --dest-cpu="${@map_nodejs_arch(d.getVar('TARGET_ARCH', True), d)}" \
               --dest-os=linux \
               ${ARCHFLAGS}
}

do_compile () {
    cd ${S}/examples/pxScene2d/external/libnode-v${PV}
    export LD="${CXX}"
    oe_runmake BUILDTYPE=Release
}

do_install () {
    cd ${S}/examples/pxScene2d/external/libnode-v${PV}
    oe_runmake install DESTDIR=${D}

    cp -av --no-preserve=ownership ${S}/examples/pxScene2d/external/libnode-v${PV}/out/Release/lib.target/libnode.so.${NODE_LIB_VERSION} ${D}/usr/lib
    cd ${D}/usr/lib
    ln -sf libnode.so.${NODE_LIB_VERSION} libnode.so

    # clean up some node stuff
    rm -rf ${D}${bindir}/libnode.so.${NODE_LIB_VERSION}
    rm -rf ${D}${datadir}/systemtap

    rm -rf ${D}${exec_prefix}/lib/node_modules/npm/node_modules/node-gyp/gyp/samples
    rm -rf ${D}${exec_prefix}/lib/node_modules
}

do_install_append_class-native() {
    # use node from PATH instead of absolute path to sysroot
    # node-v0.10.25/tools/install.py is using:
    # shebang = os.path.join(node_prefix, 'bin/node')
    # update_shebang(link_path, shebang)
    # and node_prefix can be very long path to bindir in native sysroot and
    # when it exceeds 128 character shebang limit it's stripped to incorrect path
    # and npm fails to execute like in this case with 133 characters show in log.do_install:
    # updating shebang of /home/jenkins/workspace/build-webos-nightly/device/qemux86/label/open-webos-builder/BUILD-qemux86/work/x86_64-linux/nodejs-native/0.10.15-r0/image/home/jenkins/workspace/build-webos-nightly/device/qemux86/label/open-webos-builder/BUILD-qemux86/sysroots/x86_64-linux/usr/bin/npm to /home/jenkins/workspace/build-webos-nightly/device/qemux86/label/open-webos-builder/BUILD-qemux86/sysroots/x86_64-linux/usr/bin/node
    # /usr/bin/npm is symlink to /usr/lib/node_modules/npm/bin/npm-cli.js
    # use sed on npm-cli.js because otherwise symlink is replaced with normal file and
    # npm-cli.js continues to use old shebang
    #sed "1s^.*^#\!/usr/bin/env node^g" -i ${D}${exec_prefix}/lib/node_modules/npm/bin/npm-cli.js
}

do_install_append_class-target() {
    #sed "1s^.*^#\!${bindir}/env node^g" -i ${D}${exec_prefix}/lib/node_modules/npm/bin/npm-cli.js
}


# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so ${libdir}/node_modules ${PKG_CONFIG_DIR}/*.pc"
FILES_${PN}-dbg += "${libdir}/.debug/*.so"

# ----------------------------------------------------------------------------

INSANE_SKIP_${PN} += "dev-so"
INSANE_SKIP_${PN}-tools = "file-rdeps"
#INSANE_SKIP_${PN}-dev = "file-rdeps"
