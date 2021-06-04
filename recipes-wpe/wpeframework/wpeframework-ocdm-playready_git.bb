SUMMARY = "WPE Framework OpenCDMi module for playready"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=66fe57b27abb01505f399ce4405cfea0"

require include/wpeframework-plugins.inc

DEPENDS += " playready"

SRC_URI = "\
    git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=development/modulename_fix \
    file://0001-OCDM-Playready-adjust-header-order-to-fix-build-issue.patch \
"

SRCREV = "70e69f4a5a9252f980398b6eadb53d282debbf0b"
EXTRA_OECMAKE += "\
    -DPERSISTENT_PATH=${WPEFRAMEWORK_PERSISTENT_PATH} \
"

FILES_${PN} = "\
    ${datadir}/WPEFramework/OCDM/Playready.drm \
    ${WPEFRAMEWORK_PERSISTENT_PATH}OCDM/playready \
"

