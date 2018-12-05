SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus SVP/SAGE dedicated"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Playready-Nexus-SVP.git;protocol=ssh;branch=master"
SRCREV = "02e67eec87fa7d2e8db6e5b331fd5687de2ca32a"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
