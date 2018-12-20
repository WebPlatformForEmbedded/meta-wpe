SUMMARY = "WPE Framework OpenCDMi module for widevine"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require wpeframework-plugins.inc

DEPENDS += " widevine"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Widevine.git;protocol=https;branch=master"

SRCREV = "1a4b9812df227fe9fac83b04585e33d4bb11d2d1"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
