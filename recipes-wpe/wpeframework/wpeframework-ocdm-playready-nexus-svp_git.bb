SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus SVP/SAGE dedicated"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=363ee002438e52dac11707343db81c4e"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready-Nexus-SVP.git;protocol=https;branch=master"
SRCREV = "29a1ce2fce8d4feab23a38d762decbdc7968201a"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
