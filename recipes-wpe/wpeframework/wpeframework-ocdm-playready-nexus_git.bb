SUMMARY = "WPE Framework OpenCDMi module for PlayReady Nexus"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready-Nexus.git;protocol=https;branch=master"
SRCREV = "0d39883959f8ba0bc03848b384a0f7b690910e52"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
