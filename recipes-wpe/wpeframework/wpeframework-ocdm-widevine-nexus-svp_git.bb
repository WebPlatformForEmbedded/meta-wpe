SUMMARY = "WPE Framework OpenCDMi module for Widevine Nexus SVP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Widevine-Nexus-SVP.git;protocol=https;branch=master"
SRCREV = "1ffce9422c3302ac9a13da9305b25885411ea6dc"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
