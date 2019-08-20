SUMMARY = "WPE Framework OpenCDMi module for playready"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"

inherit distro_features_check
REQUIRED_DISTRO_FEATURES = "playready"

require include/wpeframework-plugins.inc

DEPENDS += " playready"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Playready.git;protocol=https;branch=master"
SRCREV = "023a064e3e87de8a9934394fd835f57f37bbcb10"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
