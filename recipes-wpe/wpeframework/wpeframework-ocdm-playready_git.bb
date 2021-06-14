SUMMARY = "WPE Framework OpenCDMi module for playready"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=66fe57b27abb01505f399ce4405cfea0"

require include/wpeframework-plugins.inc

DEPENDS_append = " playready"

SRC_URI = "\
    git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=PR2.5 \
    file://0001-OCDM-Playready-adjust-header-order-to-fix-build-issue.patch \
"

SRCREV = "9c530ee979d67aeb07c710f6c51026ae37807936"
EXTRA_OECMAKE += "\
    -DPERSISTENT_PATH=${WPEFRAMEWORK_PERSISTENT_PATH} \
"

FILES_${PN} = "\
    ${datadir}/WPEFramework/OCDM/Playready.drm \
    ${WPEFRAMEWORK_PERSISTENT_PATH}OCDM/playready \
"

