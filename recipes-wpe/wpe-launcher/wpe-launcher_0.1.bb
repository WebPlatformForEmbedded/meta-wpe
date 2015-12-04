LICENSE = "CLOSED"

DEPENDS += "wpe glib-2.0"

SRCREV = "f1244c0e4fd9f74350f97bfe21dd7369056ee9e2"

SRC_URI = "git://github.com/Metrological/wpe-launcher.git;protocol=http;branch=master"

S = "${WORKDIR}/git"

FULL_OPTIMIZATION_remove = "-g"

inherit cmake
