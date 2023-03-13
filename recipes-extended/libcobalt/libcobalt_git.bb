SUMMARY = "Lighweigh application container compatible with W3C HTML5 spec"
DESCRIPTION = "\
    Cobalt is a lightweight application container \
    (i.e. an application runtime, like a JVM or the Flash Player) \
    that is compatible with a subset of the W3C HTML5 specifications \
"
HOMEPAGE = "https://github.com/Metrological/Cobalt"
LICENSE = "BSD-3-Clause & BSD-2-Clause & Apache-2.0 & MIT & ISC & OpenSSL & CC0-1.0 & LGPL-2.0 & LGPL-2.1 & PD & Zlib & MPL-2.0"
LIC_FILES_CHKSUM = "\
    file://LICENSE;md5=0fca02217a5d49a14dfe2d11837bb34d \
"

DEPENDS_append = " \
    bison-native \
    gstreamer1.0 \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    wpeframework-clientlibraries \
    ccache-native \
    gn-native \
    ninja-native \
    python-native \
    python3-native \
    python-six-native \
"

GCC_MAJOR_VERSION = "${@oe.utils.trim_version("${GCCVERSION}", 1)}"
GCC_9_PATCHLIST = "file://0001-changes-for-gcc-9.patch"

RECIPE_BRANCH ?= "cobalt-23"
SRC_URI = "\
    git://git@github.com/Metrological/Cobalt.git;protocol=https;branch=${RECIPE_BRANCH} \
    file://0002-data-path-config-support.patch \
"

SRC_URI_append = " ${@bb.utils.contains('GCC_MAJOR_VERSION', '9', '${GCC_9_PATCHLIST}', '', d)}"

SRCREV ??= "a772cccf1da68623f3312bb8466860e2d1964f24"
PR = "r0"
S = "${WORKDIR}/git"

inherit pythonnative

# FIXME: Check wheter necessary
PACKAGES = "${PN}"

LD = "${CXX}"

COBALT_PLATFORM ??= "wpe-arm"
COBALT_VIDEO_OVERLAY ??= "true"
COBALT_BUILD_TYPE ??= "gold"
COBALT_DEPENDENCIES ??= ""

DEPENDS_append = " ${COBALT_DEPENDENCIES}"

def get_cobalt_data_path(d):
    cobalt_data = d.getVar('WPEFRAMEWORK_DATA_PATH')
    if not cobalt_data:
        return "${datadir}/content"
    else:
        return "${WPEFRAMEWORK_DATA_PATH}/Cobalt"
COBALT_DATA = "${@get_cobalt_data_path(d)}"

do_compile() {
    export COBALT_EXECUTABLE_TYPE="shared_library"
    export COBALT_HAS_OCDM="${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 1, 0, d)}"
    export COBALT_HAS_PROVISION="${@bb.utils.contains('DISTRO_FEATURES', 'provisioning', 1, 0, d)}"
    export COBALT_HAS_WAYLANDSINK="${@bb.utils.contains('DISTRO_FEATURES', 'weston', 1, 0, d)}"
    export COBALT_VIDEO_OVERLAY=${COBALT_VIDEO_OVERLAY}

    export COBALT_STAGING_DIR="${STAGING_DIR_HOST}/"
    export OPENSSL_NO_ASM=1
    export COBALT_TOOLCHAIN_PREFIX="${STAGING_DIR_NATIVE}${bindir}/${TARGET_SYS}/${TARGET_PREFIX}"
    export PYTHONPATH="${PYTHONPATH}:${S}"
    export COBALT_DATA_PATH="${COBALT_DATA}/data"

    cd "${S}" && "${STAGING_BINDIR_NATIVE}"/gn gen out/wpe --script-executable=python3 --args='target_platform="${COBALT_PLATFORM}" build_type="${COBALT_BUILD_TYPE}" target_cpu="arm" is_clang=false sb_install_content_subdir="${COBALT_DATA}/data" is_video_overlay=${COBALT_VIDEO_OVERLAY}'
    "${STAGING_BINDIR_NATIVE}/ninja" -C "${S}"/out/wpe cobalt_install
}

do_install() {
    install -d "${D}${libdir}"
    install -m 0755 "${S}/out/wpe/install/lib/libcobalt.so" "${D}${libdir}"
    install -d "${D}${includedir}"/starboard
    cp -prf "${S}"/starboard/*.h "${D}${includedir}"/starboard/
    install -d "${D}${includedir}"/third_party/starboard/wpe/shared
    cp -prf "${S}"/third_party/starboard/wpe/shared/*.h "${D}/${includedir}"/third_party/starboard/wpe/shared/

    install -d "${D}${COBALT_DATA}"
    cp -arv --no-preserve=ownership "${S}/out/wpe/install/${COBALT_DATA}/data" "${D}${COBALT_DATA}"
}

SSTATE_DUPWHITELIST = "/"

COBALT_PACKAGE = "\
    ${libdir}/libcobalt.so \
    ${COBALT_DATA}/* \
    ${includedir}/* \
"
FILES_${PN} += "${COBALT_PACKAGE}"
FILES_${PN}-dev += "${COBALT_PACKAGE}"

RDEPENDS_${PN} += "\
    ${COBALT_DEPENDENCIES} \
    gstreamer1.0 \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    wpeframework \
"

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"
INSANE_SKIP_${PN} += "dev-deps"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

