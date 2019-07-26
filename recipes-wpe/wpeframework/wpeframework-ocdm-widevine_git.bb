SUMMARY = "WPE Framework OpenCDMi module for widevine"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"

require include/wpeframework-plugins.inc

DEPENDS += " widevine"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Widevine.git;protocol=https;branch=master"
SRCREV = "4bab6a2375b6bbaa8c8180688a6f85d26df491dd"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
