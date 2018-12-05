SUMMARY = "WPE Framework OpenCDMi module for widevine"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

require wpeframework-plugins.inc

DEPENDS += " widevine"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Widevine.git;protocol=ssh;branch=master"
SRCREV = "ac3a765bdf96027fcb4ff67b0ca69858eb5dd665"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
