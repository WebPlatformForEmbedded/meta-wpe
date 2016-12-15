SUMMARY = "WebDriver for WPE"
HOMEPAGE = "http://www.metrological.com/"
SECTION = "metrological"
LICENSE = "CLOSED"

DEPENDS = "curl glib-2.0 json-c wpe gyp-native"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/webdriver.git;protocol=ssh;branch=WD_1.X_dev \
           file://0001-debugger_posix.cc-execinfo.h-is-glibc-uclibc-specifi.patch \
           file://chromium_base.pc \
           file://WebDriver_core.pc \
           file://WebDriver_extension_wpe_base.pc \
"

SRCREV = "5668e536fcf054d9b064a2b7f77a47f40d38b442"

S = "${WORKDIR}/git"

inherit pythonnative

export WPE_STAGING_DIR = "${STAGING_DIR_TARGET}"
export WPE_TARGET_DIR = "${STAGING_DIR_TARGET}"

CPPFLAGS += "-I${STAGING_DIR_TARGET}/usr/include/glib-2.0 -I${STAGING_DIR_TARGET}/usr/lib/glib-2.0/include"
CXXFLAGS += "-D_GLIBCXX_USE_CXX11_ABI=0"

EXTRA_OEMAKE = "V=1 LIBS='-lWPEWebKit -lWPE -lglib-2.0 -ljson-c -lcurl -pthread -ldl'"

do_configure() {
    export PYTHON=python
    export GYP=`which gyp`
    rm -rf out
    ./build_wpe.sh out release
}

do_compile() {
    oe_runmake -C out/wpe/release
    ./copy.sh out release
}

do_install() {
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/*.pc ${D}${libdir}/pkgconfig/
    install -d ${D}${includedir}
    install -m 0644 ${B}/src/webdriver_wrapper/*.h  ${D}${includedir}/

    install -d ${D}${bindir}
    install -m 0755 ${B}/out/bin/wpe/release/W* ${D}${bindir}/
    install -d ${D}${libdir}
    install -m 0755 ${B}/out/bin/wpe/release/lib*.so ${D}${libdir}/
    install -d ${D}${datadir}/web
    install -m 0644 ${B}/web/* ${D}${datadir}/web/
}

FILES_SOLIBSDEV = ""

FILES_${PN} += "${libdir}/*.so ${datadir}/web"

TOOLCHAIN = "gcc"
