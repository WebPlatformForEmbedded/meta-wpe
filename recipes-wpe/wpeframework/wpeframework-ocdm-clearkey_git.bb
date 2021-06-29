SUMMARY = "WPE Framework OpenCDMi - clearkey"
DESCRIPTION = "WPE Framework OpenCDMi module for clearkey"
HOMEPAGE = "https://github.com/rdkcentral/OCDM-Clearkey"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9c83fce561d6921e20681566f2550145"

require include/wpeframework-plugins.inc

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Clearkey.git;protocol=https;branch=master"
SRCREV = "1524b947f5c28100326415a2ac1d02f3974acdc8"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"

