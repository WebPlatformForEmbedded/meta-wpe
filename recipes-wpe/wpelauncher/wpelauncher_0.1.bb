LICENSE = "CLOSED"

DEPENDS += "wpewebkit glib-2.0"

SRCREV = "f7fdafee26f9dc4be7cdb3d6239ec4577e96a38b"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEWebKitLauncher.git;protocol=git;branch=master"

S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

CXXFLAGS += "-D_GLIBCXX_USE_CXX11_ABI=0"

inherit cmake

FILES_SOLIBSDEV = ""
INSANE_SKIP ="dev-so"
FILES_${PN} += "${libdir}/*.so"
