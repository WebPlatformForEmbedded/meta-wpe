require include/wpeframework-ocdm-playready.inc

PV = "3.0+gitr${SRCPV}"
RECIPE_BRANCH ?= "master"
SRC_URI = "\
    git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=${RECIPE_BRANCH} \
    file://0001-OCDM-Playready-adjust-header-order-to-fix-build-issue.patch \
"
SRCREV ?= "bf1d4afd0873ff5e38be303a1176d1a4ab3850a3"
