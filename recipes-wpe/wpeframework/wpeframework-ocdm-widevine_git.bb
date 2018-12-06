SUMMARY = "WPE Framework OpenCDMi module for widevine"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins widevine"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Widevine.git;protocol=ssh;branch=master"

SRCREV = "15222ce093962317a8c4798e4682d27ae01ad57a"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
