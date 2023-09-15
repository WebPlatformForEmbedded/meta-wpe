SUMMARY = "WPE Framework OpenCDMi - PlayReady Nexus SVP"
DESCRIPTION = "WPE Framework OpenCDMi module for PlayReady Nexus SVP/SAGE dedicated"
HOMEPAGE = "https://code.rdkcentral.com/r/plugins/gitiles/soc/broadcom/components/rdkcentral/OCDM-Playready-Nexus-SVP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=363ee002438e52dac11707343db81c4e"

require include/wpeframework-plugins.inc

DEPENDS_append = " broadcom-refsw"

PV = "3.0+gitr${SRCPV}"
RECIPE_BRANCH ?= "master"
SRC_URI = "git://code.rdkcentral.com/r/soc/broadcom/components/rdkcentral/OCDM-Playready-Nexus-SVP.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "37f25604dd94f72cc3357f10a59beb14e0cca07a"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"

