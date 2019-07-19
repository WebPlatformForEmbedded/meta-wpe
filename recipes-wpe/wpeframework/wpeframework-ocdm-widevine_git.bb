SUMMARY = "WPE Framework OpenCDMi module for widevine"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require include/wpeframework-plugins.inc

DEPENDS += " widevine"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Widevine.git;protocol=https;branch=master"
SRCREV = "18d4e0f203c727b4fc20301f3370a24d0cf8e463"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
