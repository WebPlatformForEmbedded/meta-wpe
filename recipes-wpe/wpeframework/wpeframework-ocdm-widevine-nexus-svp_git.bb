SUMMARY = "WPE Framework OpenCDMi module for Widevine Nexus SVP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Widevine-Nexus-SVP.git;protocol=https;branch=master"
# Revision hash of R1 release
SRCREV = "a8494eaa9ee9806898150957b70c0f0e8dab0d8f"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
