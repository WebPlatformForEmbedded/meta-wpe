SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus SVP/SAGE dedicated"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready-Nexus-SVP.git;protocol=https;branch=master"
SRCREV = "2cd94a73b30d6484606f6bcfa181fb27824b815a"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
