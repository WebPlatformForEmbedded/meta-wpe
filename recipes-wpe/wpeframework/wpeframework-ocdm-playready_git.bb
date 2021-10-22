require include/wpeframework-ocdm-playready.inc

PV = "3.0+gitr${SRCPV}"
RECIPE_BRANCH ?= "master"
SRC_URI = "\
    git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=${RECIPE_BRANCH} \
    file://0001-OCDM-Playready-adjust-header-order-to-fix-build-issue.patch \
"
SRCREV ?= "92bdcd9ce3636120610df195a93e3988d9e8b0fc"
