SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require include/wpeframework-plugins.inc

DEPENDS_append = " broadcom-refsw"

PV = "3.0+gitr${SRCPV}"
SRC_URI = "git://code.rdkcentral.com/r/soc/broadcom/components/rdkcentral/OCDM-Playready-Nexus.git;protocol=https;branch=master"
SRCREV = "cdb2e784a03b2d01c03a12d008bf1d9e034f7b62"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"

