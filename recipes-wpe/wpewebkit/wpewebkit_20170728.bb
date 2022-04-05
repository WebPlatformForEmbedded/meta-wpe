require wpewebkit.inc

DEFAULT_PREFERENCE = "-1"

PV = "20170728+git${SRCPV}"
PR = "r2"
RECIPE_BRANCH ?= "wpe-20170728"
SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=${RECIPE_BRANCH};protocol=https"
SRC_URI_append = " \
    file://0001-Fix-build-with-musl.patch \
    file://0002-Define-MESA_EGL_NO_X11_HEADERS-when-not-using-GLX.patch \
"
SRCREV ?= "f9402295bed27deb780d38e33c20e397eccb009a"

do_compile() {
    ${STAGING_BINDIR_NATIVE}/ninja ${PARALLEL_MAKE} -C ${B} libWPEWebKit.so libWPEWebInspectorResources.so WPEWebProcess WPENetworkProcess WPEStorageProcess WPEWebDriver
}

do_install() {
    DESTDIR=${D} cmake -DCOMPONENT=Development -P ${B}/Source/WebKit/cmake_install.cmake
    DESTDIR=${D} cmake -DCOMPONENT=Development -P ${B}/Source/JavaScriptCore/cmake_install.cmake

    install -d ${D}${libdir}
    cp -av --no-preserve=ownership ${B}/lib/libWPE* ${D}${libdir}/
    install -m 0755 ${B}/lib/libWPEWebInspectorResources.so ${D}${libdir}/
    # Hack: Remove the RPATH embedded in libWPEWebKit.so
    chrpath --delete ${D}${libdir}/libWPE*

    install -d ${D}${bindir}
    install -m755 ${B}/bin/WPEWebProcess ${D}${bindir}/
    install -m755 ${B}/bin/WPENetworkProcess ${D}${bindir}/
    install -m755 ${B}/bin/WPEStorageProcess ${D}${bindir}/
    install -m755 ${B}/bin/WPEWebDriver ${D}${bindir}/

    # Hack: Remove RPATHs embedded in apps
    chrpath --delete ${D}${bindir}/WPEWebProcess
    chrpath --delete ${D}${bindir}/WPENetworkProcess
    chrpath --delete ${D}${bindir}/WPEStorageProcess
    chrpath --delete ${D}${bindir}/WPEWebDriver
}

