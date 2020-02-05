LICENSE = "BSD-3-Clause & BSD-2-Clause & Apache-2.0 & MIT & ISC & OpenSSL & CC0-1.0 & LGPL-2.0 & LGPL-2.1 & PD & Zlib & MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://src/LICENSE;md5=0fca02217a5d49a14dfe2d11837bb34d \
"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad ninja-native bison-native wpeframework"

SRCREV = "3fdc0d8ce722a5a430a5df26fbe251dfb4c3ae11"
SRC_URI = "git://git@github.com/Metrological/Cobalt.git;protocol=ssh; \
           file://0001-cobalt-gyp-config-oe-changes.patch \
"
S = "${WORKDIR}/git"


# TODO: we might also have mips here at some point.
COBALT_PLATFORM ?= ""
COBALT_PLATFORM_brcm ?= "wpe-brcm-arm"
COBALT_PLATFORM_rpi ?= "wpe-rpi"

COBALT_DEPENDENCIES ?= ""
COBALT_DEPENDENCIES_brcm ?= "gst1-bcm"

COBALT_BUILD_TYPE = "qa"

do_configure() {
    export COBALT_EXECUTABLE_TYPE=shared_library
    export COBALT_HAS_OCDM="${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 1, 0, d)}"

    export OE_HOME=${STAGING_DIR_HOST}/
    export OE_NATIVE=${STAGING_DIR_NATIVE}${bindir}/${TARGET_SYS}/${TARGET_PREFIX}

    export COBALT_INSTALL_DIR=${D}
    ${S}/src/cobalt/build/gyp_cobalt -C ${COBALT_BUILD_TYPE} ${COBALT_PLATFORM}
}

do_compile() {
    ${STAGING_BINDIR_NATIVE}/ninja -C ${S}/src/out/${COBALT_PLATFORM}_${COBALT_BUILD_TYPE} cobalt_deploy
}

do_install() {
    if [ -f ${STAGING_DIR_TARGET}/usr/lib/libcobalt.so ]
    then
        rm -rf ${STAGING_DIR_TARGET}/usr/lib/libcobalt.so
    fi
    install -d ${D}${libdir}
    install -m 0755 ${S}/src/out/${COBALT_PLATFORM}_${COBALT_BUILD_TYPE}/lib/libcobalt.so ${D}${libdir}

    install -d ${D}/usr/share
    cp -prf ${S}/src/out/${COBALT_PLATFORM}_${COBALT_BUILD_TYPE}/content ${D}/usr/share/
}

FILES_${PN}     = "${libdir}/libcobalt.so"
FILES_${PN}-dev = "{libdir}/libcobalt.so"
FILES_${PN}    += "usr/share/content/*"

#For dev packages only
INSANE_SKIP_${PN}-dev = "ldflags"
