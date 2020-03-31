SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus SVP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready-Nexus-SVP.git;protocol=https;branch=master"
SRCREV = "29a1ce2fce8d4feab23a38d762decbdc7968201a"


FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
