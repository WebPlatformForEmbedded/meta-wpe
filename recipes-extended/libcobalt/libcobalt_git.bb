LICENSE = "BSD-3-Clause & BSD-2-Clause & Apache-2.0 & MIT & ISC & OpenSSL & CC0-1.0 & LGPL-2.0 & LGPL-2.1 & PD & Zlib & MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://src/LICENSE;md5=0fca02217a5d49a14dfe2d11837bb34d \
"

PR = "r0"
PACKAGES = "${PN}"
DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad ninja-native bison-native wpeframework"

SRC_URI = "git://git@github.com/Metrological/Cobalt.git;protocol=https;rev=c0e5376052e4c99e9e99866198e78fcff61d42f3 \
           git://chromium.googlesource.com/chromium/tools/depot_tools.git;protocol=https;rev=44ea3ffa40fe375a8ae2e0f89968c335d08c8a8a;destsuffix=depot_tools;name=depot_tools \
"
S = "${WORKDIR}/git"

LD = "${CXX}"

# TODO: we might also have mips here at some point.
COBALT_PLATFORM ?= ""
COBALT_PLATFORM_brcm ?= "wpe-brcm-arm"
COBALT_PLATFORM_rpi ?= "wpe-rpi"

COBALT_DEPENDENCIES ?= ""
COBALT_DEPENDENCIES_brcm ?= "gstreamer-plugins-soc"
DEPENDS += "${COBALT_DEPENDENCIES}"

COBALT_BUILD_TYPE = "qa"

do_configure() {
    export PATH=$PATH:${S}/../depot_tools
    export COBALT_EXECUTABLE_TYPE=shared_library
    export COBALT_HAS_OCDM="${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 1, 0, d)}"

    export COBALT_STAGING_DIR=${STAGING_DIR_HOST}/
    export COBALT_TOOLCHAIN_PREFIX=${STAGING_DIR_NATIVE}${bindir}/${TARGET_SYS}/${TARGET_PREFIX}

    export COBALT_INSTALL_DIR=${D}
    ${S}/src/cobalt/build/gyp_cobalt -C ${COBALT_BUILD_TYPE} ${COBALT_PLATFORM}
}

do_compile() {
    export PATH=$PATH:${S}/../depot_tools
    ${STAGING_BINDIR_NATIVE}/ninja -C ${S}/src/out/${COBALT_PLATFORM}_${COBALT_BUILD_TYPE} cobalt_deploy
}

do_install() {
    export PATH=$PATH:${S}/../depot_tools
    if [ -f ${STAGING_DIR_TARGET}/usr/lib/libcobalt.so ]
    then
        rm -rf ${STAGING_DIR_TARGET}/usr/lib/libcobalt.so
    fi
    install -d ${D}${libdir}
    install -m 0755 ${S}/src/out/${COBALT_PLATFORM}_${COBALT_BUILD_TYPE}/lib/libcobalt.so ${D}${libdir}

    install -d ${D}/usr/share
    cp -prf ${S}/src/out/${COBALT_PLATFORM}_${COBALT_BUILD_TYPE}/content ${D}/usr/share/
}

COBALT_PACKAGE = " \
    ${libdir}/libcobalt.so \
    ${datadir}/content/* \
"
FILES_${PN}     = "${COBALT_PACKAGE}"
FILES_${PN}-dev = "${COBALT_PACKAGE}"

#For dev packages only
INSANE_SKIP_${PN}-dev = "ldflags"
