LICENSE = "CLOSED"

DEPENDS += "wpe glib-2.0"

SRCREV = "75524f3779973b8009c8d36c06f65006790f752a"

SRC_URI = "git://github.com/Metrological/wpe-launcher.git;protocol=http;branch=master"

S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

inherit cmake

FILES_SOLIBSDEV = ""
INSANE_SKIP ="dev-so"
FILES_${PN} += "${libdir}/*.so"
