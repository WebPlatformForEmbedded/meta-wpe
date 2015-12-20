LICENSE = "CLOSED"

DEPENDS += "wpe glib-2.0"

SRCREV = "fc1c4ceeb2e5d9c102a4d7e6ae5f43625e79d9b5"

SRC_URI = "git://github.com/Metrological/wpe-launcher.git;protocol=http;branch=master"

S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

inherit cmake
