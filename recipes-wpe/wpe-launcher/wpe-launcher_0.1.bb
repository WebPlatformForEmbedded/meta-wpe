LICENSE = "CLOSED"

DEPENDS += "wpe glib-2.0"

SRCREV = "8f6d5b57cc1a36ef7a01db4f3a395b6e082203f4"

SRC_URI = "git://github.com/Metrological/wpe-launcher.git;protocol=http;branch=master"

S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

inherit cmake

FILES_SOLIBSDEV = ""
INSANE_SKIP ="dev-so"
FILES_${PN} += "${libdir}/*.so"
