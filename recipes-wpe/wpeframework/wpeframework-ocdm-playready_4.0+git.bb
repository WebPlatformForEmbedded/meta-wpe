require include/wpeframework-ocdm-playready.inc

PV = "4.0+git${SRCPV}"
RECIPE_BRANCH ?= "master"
SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "bf1d4afd0873ff5e38be303a1176d1a4ab3850a3"

TARGET_CFLAGS += "-fpermissive"

RDEPENDS_${PN}_append = " playready"
