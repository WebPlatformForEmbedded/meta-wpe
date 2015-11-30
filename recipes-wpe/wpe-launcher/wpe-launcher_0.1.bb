LICENSE = "CLOSED"

DEPENDS += "wpe glib-2.0"

SRCREV = "dc657ad15ff8310797f2859f7108b4e329e490f2"

SRC_URI = "git://github.com/Metrological/wpe-launcher.git;protocol=http;branch=master"

S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

inherit cmake
