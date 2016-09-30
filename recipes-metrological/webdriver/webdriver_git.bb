SUMMARY = "WebDriver for WPE"
HOMEPAGE = "http://www.metrological.com/"
SECTION = "metrological"
LICENSE = "CLOSED"

DEPENDS = "curl glib-2.0 json-c wpe gyp-native"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/webdriver.git;protocol=ssh;branch=wpewebdriver"
SRC_URI += " \
    file://chromium_base.pc                \
    file://WebDriver_core.pc               \
    file://WebDriver_extension_wpe_base.pc \
"

SRCREV = "6b7f24d44470f13d467140c3afe28b326177d91d"

S = "${WORKDIR}/git"

inherit pythonnative

export WPE_STAGING_DIR = "${STAGING_DIR_TARGET}"
export WPE_TARGET_DIR = "${STAGING_DIR_TARGET}"

GLIB_INC = "${STAGING_DIR_TARGET}/usr/include/glib-2.0"
GLIB_LIB_INC = "${STAGING_DIR_TARGET}/usr/lib/glib-2.0/include"
WD_PLATFORM_NAME = "wpe"

do_configure() {
    cd ${S}
    rm -rf out
    ./build_wpe.sh out release
}

do_compile() {
    oe_runmake CPPFLAGS="${CPPFLAGS} -I${GLIB_INC} -I${GLIB_LIB_INC}" \
    LIBS="-L${STAGING_DIR_TARGET}/usr/lib/ -lWPEWebKit -lWPE -lglib-2.0 -ljson-c -lcurl -pthread -ldl" \
    -C ${S}/out/${WD_PLATFORM_NAME}/release/;
    cd ${S};./copy.sh out release;
}

do_install() {
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/*.pc ${D}${libdir}/pkgconfig/
    install -d ${D}${includedir}/web
    install -m 0755 ${B}/src/webdriver_wrapper/*.h  ${D}${includedir}/

    install -d ${D}${bindir}
    install -m 0755 ${B}/out/bin/${WD_PLATFORM_NAME}/release/W* ${D}${bindir}/
    install -d ${D}${libdir}
    install -m 0755 ${B}/out/bin/${WD_PLATFORM_NAME}/release/lib*.so ${D}${libdir}/
    install -d ${D}${datadir}/web
    install -m 0755 ${B}/web/* ${D}${datadir}/web/
}

FILES_SOLIBS = ""
FILES_SOLIBSDEV = ""

FILES_${PN} = "${bindir}/W* \
               ${libdir}/*.so \
               ${datadir}/web \
               ${includedir}/*.h" 

TOOLCHAIN = "gcc"
