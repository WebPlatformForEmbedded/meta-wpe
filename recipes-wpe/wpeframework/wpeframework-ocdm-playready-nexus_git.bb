SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins broadcom-refsw"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Playready-Nexus.git;protocol=ssh;branch=master"
SRCREV = "67fa41c0636671589c81103d91b712846a9a11f8"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
