SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus SVP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Playready-Nexus-SVP.git;protocol=https;branch=master"
SRCREV = "7afe25acb014df68068afffe99fdb5355d311d87"


FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
