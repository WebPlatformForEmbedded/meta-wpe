SUMMARY = "WPE Framework OpenCDMi module for playready"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"

require include/wpeframework-plugins.inc

DEPENDS += " playready"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=master"
# Revision hash of R1 release
SRCREV = "f1346e55dba19c1002f26aaacde046ee46fc2bd3"

EXTRA_OECMAKE += " \
    -DPERSISTENT_PATH=${WPEFRAMEWORK_PERSISTENT_PATH} \
"

FILES_${PN} = " \
    ${datadir}/WPEFramework/OCDM/*.drm \
    ${WPEFRAMEWORK_PERSISTENT_PATH}/OCDM/playready \
"
FILES_${PN}-dbg += "${WPEFRAMEWORK_PERSISTENT_PATH}"
