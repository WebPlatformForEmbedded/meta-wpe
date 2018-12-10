SUMMARY = "WPE Framework OpenCDMi module for playready"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins playready"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Playready.git;protocol=ssh;branch=master"
SRCREV = "e9d38d7ffddfe00c4715b140fb82ab6435cd3046"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
