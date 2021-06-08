SUMMARY = "WPE Framework OpenCDMi module for playready"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=66fe57b27abb01505f399ce4405cfea0"

require wpeframework-plugins.inc

DEPENDS += " playready"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=${SRCBRANCH}"

EXTRA_OECMAKE += "-DPERSISTENT_PATH=${WPEFRAMEWORK_PERSISTENT_PATH}"

FILES_${PN} = "\
    ${datadir}/WPEFramework/OCDM/Playready.drm \
    ${WPEFRAMEWORK_PERSISTENT_PATH}OCDM/playready \
"

