require wpewebkit.inc

DEPENDS_append = " libgcrypt"

PV = "2.22+git${SRCPV}"
PR = "r0"
RECIPE_BRANCH ?= "wpe-2.22"
SRC_URI = "\
    git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=${RECIPE_BRANCH};protocol=https \
    file://0001-WPEWebkit-compile-fix.patch \
    file://0001-Fix-for-missing-heap-vm-main.patch \
"
SRCREV ?= "57bbecb9421d206f40a3beb45b9d800288a39b1d"

PACKAGECONFIG_append = " webcrypto"

do_compile() {
    ${STAGING_BINDIR_NATIVE}/ninja ${PARALLEL_MAKE} -C ${B} all
}

EXTRA_OECMAKE += "\
    -DEXPORT_DEPRECATED_WEBKIT2_C_API=ON \
    -DUSE_LD_GOLD=OFF \
" 

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

INSANE_SKIP_${PN}-dev = "dev-elf"
FILES_${PN}-web-inspector-plugin += "${libdir}/wpe-webkit-0.1/libWPEWebInspectorResources.so"

RCONFLICTS_${PN} = "libwpe (< 1.0)"

