SUMMARY = "WPE Framework OpenCDMi module for playready"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c2b3f2a8aff73c673037a89bee1ee396"

require include/wpeframework-plugins.inc

DEPENDS += " playready"

SRC_URI = "\
    git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=master \
    file://0001-OCDM-Playready-adjust-header-order-to-fix-build-issue.patch \
"

SRCREV = "a233e3f472ff3847fc1c4f7759c7172d4ca4ceee"
EXTRA_OECMAKE += "\
    -DPERSISTENT_PATH=${WPEFRAMEWORK_PERSISTENT_PATH} \
"

FILES_${PN} = "\
    ${datadir}/WPEFramework/OCDM/Playready.drm \
    ${WPEFRAMEWORK_PERSISTENT_PATH}OCDM/playready \
"
