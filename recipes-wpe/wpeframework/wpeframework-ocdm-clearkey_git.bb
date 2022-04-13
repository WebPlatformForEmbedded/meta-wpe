SUMMARY = "WPE Framework OpenCDMi - clearkey"
DESCRIPTION = "WPE Framework OpenCDMi module for clearkey"
HOMEPAGE = "https://github.com/rdkcentral/OCDM-Clearkey"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9c83fce561d6921e20681566f2550145"

require include/wpeframework-plugins.inc

RECIPE_BRANCH ?= "master"
SRC_URI = "git://git@github.com/rdkcentral/OCDM-Clearkey.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "84277d73751275a514976f9fcee0bfb8c6720dc1"

FILES:${PN} = "${datadir}/WPEFramework/OCDM/*.drm"

