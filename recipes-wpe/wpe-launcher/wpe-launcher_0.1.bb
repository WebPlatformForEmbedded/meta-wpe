LICENSE = "CLOSED"

DEPENDS += "wpe glib-2.0"

SRCREV = "dfa5012be0bdbc2521235456a4e85b6a1e4fce37"

SRC_URI = "git://github.com/Metrological/wpe-launcher.git;protocol=http;branch=master"

S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

inherit cmake
