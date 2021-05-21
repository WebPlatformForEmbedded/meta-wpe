LICENSE = "BSD-3-Clause & BSD-2-Clause & Apache-2.0 & MIT & ISC & OpenSSL & CC0-1.0 & LGPL-2.0 & LGPL-2.1 & PD & Zlib & MPL-2.0"
LIC_FILES_CHKSUM = "\
    file://src/LICENSE;md5=0fca02217a5d49a14dfe2d11837bb34d \
"
PR = "r0"
PACKAGES = "${PN}"

inherit pythonnative
DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad python-native ninja-native bison-native wpeframework-clientlibraries"

SRC_URI = "git://git@github.com/Metrological/Cobalt.git;protocol=https;branch=development/rpi-gcc9-compile"
SRCREV ??= "3caeb22104e97b6048651fd9f9c53dfe91655553"

S = "${WORKDIR}/git"

LD = "${CXX}"

COBALT_ARCH ??= ""
COBALT_PLATFORM ??= ""
COBALT_PLATFORM_NAME ??= ""

COBALT_DEPENDENCIES ??= ""
DEPENDS += "${COBALT_DEPENDENCIES}"

RDEPENDS_${PN} += "gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad wpeframework ${COBALT_DEPENDENCIES}"

COBALT_BUILD_TYPE ??= "gold"

do_configure() {
    export PATH="$PATH:${S}/depot_tools"
    export COBALT_EXECUTABLE_TYPE="shared_library"
    export COBALT_HAS_OCDM="${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 1, 0, d)}"
    export COBALT_HAS_PROVISION="${@bb.utils.contains('DISTRO_FEATURES', 'provisioning', 1, 0, d)}"

    export COBALT_STAGING_DIR="${STAGING_DIR_HOST}/"
    export COBALT_TOOLCHAIN_PREFIX="${STAGING_DIR_NATIVE}${bindir}/${TARGET_SYS}/${TARGET_PREFIX}"

    export COBALT_INSTALL_DIR="${D}"
    "${S}/src/cobalt/build/gyp_cobalt" -C "${COBALT_BUILD_TYPE}" "${COBALT_PLATFORM_NAME}"
}

do_compile() {
    export PATH="$PATH:${S}/depot_tools"
    "${STAGING_BINDIR_NATIVE}/ninja" -C "${S}/src/out/${COBALT_PLATFORM_NAME}_${COBALT_BUILD_TYPE}" cobalt_deploy
}

do_install() {
    export PATH="$PATH:${S}/../depot_tools"
    if [ -f "${STAGING_DIR_TARGET}/usr/lib/libcobalt.so" ]
    then
        rm -rf "${STAGING_DIR_TARGET}/usr/lib/libcobalt.so"
    fi
    install -d "${D}${libdir}"
    install -m 0755 "${S}/src/out/${COBALT_PLATFORM_NAME}_${COBALT_BUILD_TYPE}/lib/libcobalt.so" "${D}${libdir}"

    install -d "${D}${datadir}/content"
    cp -arv --no-preserve=ownership "${S}/src/out/${COBALT_PLATFORM_NAME}_${COBALT_BUILD_TYPE}/content" "${D}${datadir}/"

    install -d "${D}/${includedir}/third_party/starboard/wpe/${COBALT_PLATFORM}/${COBALT_ARCH}"
    cp -prf "${S}/src/third_party/starboard/wpe/${COBALT_PLATFORM}/${COBALT_ARCH}/"*.h "${D}/${includedir}/third_party/starboard/wpe/${COBALT_PLATFORM}/${COBALT_ARCH}/"
    install -d "${D}/${includedir}/third_party/starboard/wpe/shared"
    cp -prf "${S}/src/third_party/starboard/wpe/shared/"*.h "${D}/${includedir}/third_party/starboard/wpe/shared/"

    install -d "${D}/${includedir}/starboard"
    cp -prf "${S}/src/starboard/"*.h "${D}/${includedir}/starboard/"
}

SSTATE_DUPWHITELIST = "/"

COBALT_PACKAGE = " \
    ${libdir}/libcobalt.so \
    ${datadir}/content/* \
    ${includedir}/* \
"
FILES_${PN}     = "${COBALT_PACKAGE}"
FILES_${PN}-dev = "${COBALT_PACKAGE}"

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"
INSANE_SKIP_${PN} += "dev-deps"

INHIBIT_PACKAGE_DEBUG_SPLIT = '1'
INHIBIT_PACKAGE_STRIP = '1'
