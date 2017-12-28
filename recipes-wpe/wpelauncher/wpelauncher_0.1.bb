LICENSE = "CLOSED"

DEPENDS += "wpewebkit glib-2.0"

SRCREV = "bee8c4dfbb437e1865b93f96898078b9279220ef"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEWebKitLauncher.git;protocol=git;branch=master \
           file://wpe \
"
S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

CXXFLAGS += "-D_GLIBCXX_USE_CXX11_ABI=0"

inherit cmake

do_install_append() {
    install -m 0755 ${WORKDIR}/wpe ${D}${bindir}
}

FILES_SOLIBSDEV = ""
INSANE_SKIP ="dev-so"
FILES_${PN} += "${libdir}/*.so ${bindir}/wpe"
