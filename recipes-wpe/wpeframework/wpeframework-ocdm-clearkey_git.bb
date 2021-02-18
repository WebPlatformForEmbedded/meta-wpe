SUMMARY = "WPE Framework OpenCDMi module for clearkey"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9c83fce561d6921e20681566f2550145"

require include/wpeframework-plugins.inc

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Clearkey.git;protocol=https;branch=master"
# Revision hash of R1 release
SRCREV = "67681eabdf7aff0edc2dc1051277053307887e6b"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
