SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins broadcom-refsw"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Playready-Nexus.git;protocol=ssh;branch=master"
SRCREV = "55dd99220c5705c6dadc212501d75e0832bb36c0"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
