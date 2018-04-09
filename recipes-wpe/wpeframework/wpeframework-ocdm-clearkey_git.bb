SUMMARY = "WPE Framework OpenCDMi module for clearkey"
LICENSE = "CLOSED"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Clearkey.git;protocol=ssh;branch=master"
SRCREV = "5ef8fc0c40d7c6dbe8344c93f20d3b25f5c39135"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
