SUMMARY = "WPE Framework OpenCDMi module for playready"
LICENSE = "CLOSED"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins playready"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Playready.git;protocol=ssh;branch=master"
SRCREV = "089818c9f6db7cb0a8f7de9a4912a14fbd5f28dc"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
