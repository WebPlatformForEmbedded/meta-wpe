SUMMARY = "WPE Framework OpenCDMi module for playready"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins playready"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Playready.git;protocol=ssh;branch=master"
SRCREV = "6377079d23a18597b290dbdb6fd6dc4555bff8a1"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
