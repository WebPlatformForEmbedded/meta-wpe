SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus SVP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins broadcom-refsw"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Playready-Nexus-SVP.git;protocol=ssh;branch=master"
SRCREV = "dcc0e11f61d08fb7cf9a5f50e459ab47f190ec4c"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
