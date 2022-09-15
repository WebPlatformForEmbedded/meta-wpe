SUMMARY = "WPE Framework OpenCDMi - Widevine"
DESCRIPTION = "WPE Framework OpenCDMi module for widevine"
HOMEPAGE = "https://github.com/rdkcentral/OCDM-Widevine"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1384d4b11dad572771bc2dad384029a6"

require include/wpeframework-plugins.inc

DEPENDS_append = " widevine"

PV = "3.0+gitr${SRCPV}"
RECIPE_BRANCH ?= "master"
SRC_URI = "git://git@github.com/rdkcentral/OCDM-Widevine.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "8182af4a00a8a0f53f9e75afa470f084a26d1823"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"

