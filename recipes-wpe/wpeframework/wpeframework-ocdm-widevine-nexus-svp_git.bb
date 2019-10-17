SUMMARY = "WPE Framework OpenCDMi module for Widevine Nexus SVP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Widevine-Nexus-SVP.git;protocol=https;branch=master"
SRCREV = "f8839c1c545c350b00d32e2e1fc339b6f1228ad7"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
