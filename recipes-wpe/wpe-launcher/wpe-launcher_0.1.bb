LICENSE = "CLOSED"

DEPENDS += "wpe glib-2.0"

SRCREV = "e8447cbcad83d124d848893af306a75cbed2580e"

SRC_URI = "git://github.com/Metrological/wpe-launcher.git;protocol=http;branch=master"

S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

inherit cmake

FILES_SOLIBSDEV = ""
INSANE_SKIP ="dev-so"
FILES_${PN} += "${libdir}/*.so"
