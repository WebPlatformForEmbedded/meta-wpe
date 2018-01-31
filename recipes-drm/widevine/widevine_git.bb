SUMMARY = "Widevine DRM implementation."
HOMEPAGE = "https://www.widevine.com/"
LICENSE = "CLOSED"

DEPENDS = "gyp python-native protobuf protobuf-native"

PV = "3.1.gitr${SRCPV}"

SRCREV = "f4b9b4d78278c48612ff26559d39706d430d311a"

SRC_URI = "git://git@github.com/Metrological/widevine.git;protocol=ssh;branch=yocto"

inherit pythonnative pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[debug] = ",,"

S = "${WORKDIR}/git"

WIDEVINE_BOARD ?= ""
WIDEVINE_BOARD_rpi = "rpi"

export WV_BOARD="${WIDEVINE_BOARD}"
export WV_CC="${CC}"
export WV_CXX="${CXX}"
export WV_AR="${AR}"
export WV_HOST_CXX_FLAGS = "${CXXFLAGS}"
export WV_HOST_CC = "${CC}"
export WV_HOST_CXX = "${CXX}"
export WV_STAGING = "${STAGING_DIR_TARGET}"
export WV_STAGING_NATIVE = "${STAGING_DIR_NATIVE}"

do_configure() {
    (cd ${B};rm -rf out; rm -rf Makefile;\
    find . -name \*.mk -delete;\
    find . -name \*.pyc -delete;)
}

do_compile() {
    # build in release with -r or -c Release
    cd ${B}; ./build.py ${@bb.utils.contains("PACKAGECONFIG", "debug", "", "-r", d)} arm
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/out/arm/${@bb.utils.contains("PACKAGECONFIG", "debug", "Debug", "Release", d)}/widevine_ce_cdm_unittest ${D}${bindir}/
    install -d ${D}${libdir}
    install -m 0755 ${B}/out/arm/${@bb.utils.contains("PACKAGECONFIG", "debug", "Debug", "Release", d)}/lib.target/*.so ${D}${libdir}/

    install -d ${D}${includedir}
    install -m 0644 ${S}/cdm/include/*.h ${D}${includedir}/
    install -m 0644 ${S}/core/include/*.h ${D}${includedir}/

    install -d ${D}${includedir}/host/rpi/
    install -m 0644 ${S}/cdm/src/host/rpi/*.h ${D}${includedir}/host/
}

do_clean[noexec] = "1"
do_buildclean[noexec] = "1"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so ${includedir}/ ${includedir}/host/ ${bindir}/widevine_ce_cdm_unittest"
