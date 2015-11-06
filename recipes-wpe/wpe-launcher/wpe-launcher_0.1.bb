LICENSE = "CLOSED"

DEPENDS += "wpe glib-2.0"

SRC_URI = "git://github.com/Metrological/wpe-launcher.git;protocol=http;rev=779b63c738abeb842519a7d6f26b617c18d44084;branch=intelce"

S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

inherit cmake

