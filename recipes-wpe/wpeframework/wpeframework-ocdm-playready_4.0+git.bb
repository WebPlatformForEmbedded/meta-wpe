require include/wpeframework-ocdm-playready.inc

PV = "4.0+git${SRCPV}"
RECIPE_BRANCH ?= "master"
SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "a2261bec03cf3f028a007b94f77f4a8a02d24f5a"

TARGET_CFLAGS += "-fpermissive"

RDEPENDS_${PN}_append = " playready"
