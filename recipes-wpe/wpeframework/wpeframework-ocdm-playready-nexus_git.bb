SUMMARY = "WPE Framework OpenCDMi - PlayReady Nexus"
DESCRIPTION = "WPE Framework OpenCDMi module for PlayReady Nexus"
HOMEPAGE = "https://code.rdkcentral.com/r/plugins/gitiles/soc/broadcom/components/rdkcentral/OCDM-Playready-Nexus"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require include/wpeframework-plugins.inc

DEPENDS:append = " broadcom-refsw"

PV = "3.0+gitr${SRCPV}"
RECIPE_BRANCH ?= "master"
SRC_URI = "git://code.rdkcentral.com/r/soc/broadcom/components/rdkcentral/OCDM-Playready-Nexus.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "cdb2e784a03b2d01c03a12d008bf1d9e034f7b62"

FILES:${PN} = "${datadir}/WPEFramework/OCDM/*.drm"

