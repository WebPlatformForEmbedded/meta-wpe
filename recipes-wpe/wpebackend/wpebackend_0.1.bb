LICENSE = "BSD 2-Clause/3-Clause"
DEPENDS += "virtual/egl libxkbcommon"

SRCREV = "6955316a9c885a0325f3d1adab7062ce3a12810b"

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
FILES_${PN} += "${libdir}/libWPEBackend-0.1.so ${libdir}/pkgconfig/wpe.pc"
INSANE_SKIP_${PN} ="dev-so"

RDEPENDS_${PN} = "xkeyboard-config"
RDEPENDS_${PN}_rpi = "wpebackend-rdk"
