SUMMARY = "WPE Framework OpenCDMi module for playready"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"

require include/wpeframework-plugins.inc

DEPENDS += " playready"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Playready.git;protocol=https;branch=master"
SRCREV = "7b2a28fbdd4c427c3c7195651f588594db69216a"

EXTRA_OECMAKE += " \
    -DPERSISTENT_PATH=${WPEFRAMEWORK_PERSISTENT_PATH} \
"

FILES_${PN} = " \
    ${datadir}/WPEFramework/OCDM/*.drm \
    ${WPEFRAMEWORK_PERSISTENT_PATH}/OCDM/playready \
"
FILES_${PN}-dbg += "${WPEFRAMEWORK_PERSISTENT_PATH}"
