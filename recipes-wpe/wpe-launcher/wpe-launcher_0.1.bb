LICENSE = "CLOSED"

DEPENDS += "wpe glib-2.0"

SRCREV = "7e218aacc728e574e4d8f6df1c4b354d7d812451"

SRC_URI = "git://github.com/Metrological/wpe-launcher.git;protocol=http;branch=master"

S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

inherit cmake

FILES_SOLIBSDEV = ""
INSANE_SKIP ="dev-so"
FILES_${PN} += "${libdir}/*.so"
