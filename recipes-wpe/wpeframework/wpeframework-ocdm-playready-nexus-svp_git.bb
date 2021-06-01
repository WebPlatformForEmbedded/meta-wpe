SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus SVP/SAGE dedicated"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=363ee002438e52dac11707343db81c4e"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRCREV = "d1a32e179acb07993d235d66316190b1ea8b5ebf"
SRC_URI = "git://code.rdkcentral.com/r/soc/broadcom/components/rdkcentral/OCDM-Playready-Nexus-SVP.git;protocol=https;branch=master"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
