LICENSE = "CLOSED"
DEPENDS += "virtual/egl"

SRCREV = "5e2a29d2ccc637f7122ba72c1d62ef669b42f05c"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEBackend.git;protocol=http;branch=master"

S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

inherit cmake

CXXFLAGS += " \
-D_GLIBCXX_USE_CXX11_ABI=0 \
-D_GNU_SOURCE \
"

CFLAGS += " \
-D_GNU_SOURCE \
"

FILES_SOLIBSDEV = ""
INSANE_SKIP ="dev-so"
FILES_${PN} += "${libdir}/*.so ${libdir}/pkgconfig/wpe.pc"
