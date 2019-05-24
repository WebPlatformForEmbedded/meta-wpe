SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus SVP/SAGE dedicated"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Playready-Nexus-SVP.git;protocol=https;branch=master"
SRCREV = "182fa3af3a3f8e4865d8d577640c465fe2e4be3e"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
