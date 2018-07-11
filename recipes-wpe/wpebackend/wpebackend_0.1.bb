LICENSE = "CLOSED"
DEPENDS += "virtual/egl"

SRCREV = "8e5b96fadea9d32515ee590417925878dfa49045"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEBackend.git"

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
FILES_${PN} += "${libdir}/libWPEBackend.so ${libdir}/pkgconfig/wpe.pc"
INSANE_SKIP_${PN} ="dev-so"

RDEPENDS_${PN}_rpi = "wpebackend-rdk"

