LICENSE = "CLOSED"

DEPENDS += "wpe glib-2.0"

SRCREV = "7801e6b4a43b48aff1236e3e69f5f7f1c200e5a3"

SRC_URI = "git://github.com/Metrological/wpe-launcher.git;protocol=http;branch=master"

S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

inherit cmake

