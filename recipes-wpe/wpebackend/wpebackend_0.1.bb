LICENSE = "CLOSED"
DEPENDS += "virtual/egl"

SRCREV = "3013faaebab42e7ef7497b3850a2902978eb3d6f"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEBackend.git;protocol=git;branch=master"

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

