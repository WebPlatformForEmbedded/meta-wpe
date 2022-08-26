require include/wpeframework-ocdm-playready.inc

PV = "4.0+git${SRCPV}"
RECIPE_BRANCH ?= "master"
SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "c88e8bbc07782637997558b4f6d96db2ae75ffa5"

TARGET_CFLAGS += "-fpermissive"

RDEPENDS_${PN}_append = " playready"
