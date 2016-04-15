LICENSE = "CLOSED"

DEPENDS += "wpe glib-2.0"

SRCREV = "b6fe83f07a45824471be1ae9ea4d958f118a556d"

SRC_URI = "git://github.com/Metrological/wpe-launcher.git;protocol=http;branch=master"

S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

inherit cmake

FILES_SOLIBSDEV = ""
INSANE_SKIP ="dev-so"
FILES_${PN} += "${libdir}/*.so"
