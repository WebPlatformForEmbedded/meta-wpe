SUMMARY = "WPE Framework OpenCDMi module for widevine"
LICENSE = "CLOSED"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins widevine"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Widevine.git;protocol=ssh;branch=master"
SRCREV = "0684a8564943a6f3b5fa5e07066f2d6a1059a3d6"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
