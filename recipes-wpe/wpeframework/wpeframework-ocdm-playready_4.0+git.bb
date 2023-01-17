require include/wpeframework-ocdm-playready.inc

PV = "4.0+git${SRCPV}"
RECIPE_BRANCH ?= "R4"
SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "6db28a0d13edfb1e9fb912578f8ccb155575eddd"

TARGET_CFLAGS += "-fpermissive"

RDEPENDS_${PN}_append = " playready"
