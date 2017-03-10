LICENSE = "CLOSED"

DEPENDS += "wpewebkit glib-2.0"

SRCREV = "9c8c85c278687f5cd658dd8e6cdfaaf3f035e8e9"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEWebKitLauncher.git;protocol=http;branch=master"

S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

CXXFLAGS += "-D_GLIBCXX_USE_CXX11_ABI=0"

inherit cmake

FILES_SOLIBSDEV = ""
INSANE_SKIP ="dev-so"
FILES_${PN} += "${libdir}/*.so"
