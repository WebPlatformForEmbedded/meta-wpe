require include/wpeframework-ocdm-playready.inc

PV = "3.0+gitr${SRCPV}"
RECIPE_BRANCH ?= "R4"
SRC_URI = "\
    git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=${RECIPE_BRANCH} \
    file://0001-OCDM-Playready-adjust-header-order-to-fix-build-issue.patch \
"
SRCREV ?= "6db28a0d13edfb1e9fb912578f8ccb155575eddd"

