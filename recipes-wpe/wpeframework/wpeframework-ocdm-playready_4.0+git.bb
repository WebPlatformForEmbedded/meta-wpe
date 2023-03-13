require include/wpeframework-ocdm-playready.inc

PV = "4.0+git${SRCPV}"
RECIPE_BRANCH ?= "master"
SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "2cab9c6c0e59e54a6236f00f1545e18acdb9b1a2"

TARGET_CFLAGS += "-fpermissive"

RDEPENDS_${PN}_append = " playready"
