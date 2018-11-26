SUMMARY = "WPE Framework OpenCDMi module for clearkey"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Clearkey.git;protocol=ssh;branch=master"
SRCREV = "82bb9369c9087d9b5df8555c3266bd3d4b5d5c9f"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
