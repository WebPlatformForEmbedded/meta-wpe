include spark.inc

DEPENDS += " python-native"

COMPATIBLE_MACHINE_armv4 = "(!.*armv4).*"
COMPATIBLE_MACHINE_armv5 = "(!.*armv5).*"
COMPATIBLE_MACHINE_mips64 = "(!.*mips64).*"

inherit cmake

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
    sed "1s^.*^#\!/usr/bin/env node^g" -i ${D}${exec_prefix}/lib/node_modules/npm/bin/npm-cli.js
}

do_install_append_class-target() {
    sed "1s^.*^#\!${bindir}/env node^g" -i ${D}${exec_prefix}/lib/node_modules/npm/bin/npm-cli.js
}


# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so ${libdir}/node_modules ${PKG_CONFIG_DIR}/*.pc"
FILES_${PN}-dbg += "${libdir}/.debug/*.so"

# ----------------------------------------------------------------------------

INSANE_SKIP_${PN} += "dev-so"
#INSANE_SKIP_${PN}-dbg += "dev-so"
