SUMMARY = "WPE Framework OpenCDMi module for widevine"
LICENSE = "CLOSED"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins widevine"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Widevine.git;protocol=ssh;branch=master"
SRCREV = "36241126c7c56eafdb5f3a40d4d0158d46d28b2e"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
