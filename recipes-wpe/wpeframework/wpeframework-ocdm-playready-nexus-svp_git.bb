SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus SVP/SAGE dedicated"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=363ee002438e52dac11707343db81c4e"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRC_URI = "git://code.rdkcentral.com/r/soc/broadcom/components/rdkcentral/OCDM-Playready-Nexus-SVP.git;protocol=http;branch=master"
SRCREV = "69cedd51e040c0b592c77de59b4060937099d2f1"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"

