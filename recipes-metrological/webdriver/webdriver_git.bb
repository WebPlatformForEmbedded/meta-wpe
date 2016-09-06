SUMMARY = "WebDriver for WPE"
HOMEPAGE = "http://www.metrological.com/"
SECTION = "metrological"
LICENSE = "CLOSED"

DEPENDS = "wpe glib-2.0 json-c"
RDEPENDS_${PN} += "libcurl"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/webdriver.git;protocol=ssh;branch=wpewebdriver"
SRC_URI += " \
    file://chromium_base.pc                \
    file://WebDriver_core.pc               \
    file://WebDriver_extension_wpe_base.pc \
"

SRCREV = "4ddd0d4cae62ea89c0b83b364a4e70655c331ea8"

S = "${WORKDIR}/git"

export WPE_STAGING_DIR = "${STAGING_DIR_TARGET}"
export WPE_TARGET_DIR = "${STAGING_DIR_TARGET}"

GLIB_INC = "${STAGING_DIR_TARGET}/usr/include/glib-2.0"
GLIB_LIB_INC = "${STAGING_DIR_TARGET}/usr/lib/glib-2.0/include"

do_configure() {
    cd ${S}
    rm -rf out
    ./build_rpi.sh out rpi release
}

do_compile() {
    oe_runmake CPPFLAGS="${CPPFLAGS} -I${GLIB_INC} -I${GLIB_LIB_INC}" \
    LIBS="-L${STAGING_DIR_TARGET}/usr/lib/ -lWPEWebKit -lWPE -lglib-2.0 -ljson-c -lcurl -pthread -ldl" \
    -C ${S}/out/rpi/release/;
    cd ${S};./copy.sh out rpi release;
}

do_install() {
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/*.pc ${D}${libdir}/pkgconfig/
    install -d ${D}${includedir}/web
    install -m 0755 ${B}/src/webdriver_wrapper/*.h  ${D}${includedir}/

    install -d ${D}${bindir}
    install -m 0755 ${B}/out/bin/rpi/release/W* ${D}${bindir}/
    install -d ${D}${libdir}
    install -m 0755 ${B}/out/bin/rpi/release/lib*.so ${D}${libdir}/
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
