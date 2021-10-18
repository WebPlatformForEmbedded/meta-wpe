require include/wpeframework-ocdm-playready.inc

PV = "4.0+git${SRCPV}"
RECIPE_BRANCH ?= "master"
SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "92bdcd9ce3636120610df195a93e3988d9e8b0fc"

TARGET_CFLAGS += "-fpermissive"

RDEPENDS_${PN}_append = " playready"
