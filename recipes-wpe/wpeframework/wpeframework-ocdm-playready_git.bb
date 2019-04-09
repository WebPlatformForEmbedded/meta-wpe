SUMMARY = "WPE Framework OpenCDMi module for playready"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require include/wpeframework-plugins.inc

DEPENDS += " playready"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Playready.git;protocol=https;branch=master"
SRCREV = "170d467ace91f5e9e4cda6aadfbf1fe2994bde37"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
