SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus"
LICENSE = "CLOSED"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins broadcom-refsw"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Playready-Nexus.git;protocol=ssh;branch=master"
SRCREV = "d13d7e3cde6a8c4d9c8df85d51d75ed5e63e2e00"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
