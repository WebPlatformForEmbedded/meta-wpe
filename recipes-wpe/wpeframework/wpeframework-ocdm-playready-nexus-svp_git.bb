SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus SVP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Playready-Nexus-SVP.git;protocol=https;branch=development/new-ocdm-interface"
SRCREV = "34f49ef7a0bb1d6495c6a19d8f4863bc8f9dc032"


FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
