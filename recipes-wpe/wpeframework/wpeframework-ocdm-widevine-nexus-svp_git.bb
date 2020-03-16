SUMMARY = "WPE Framework OpenCDMi module for Widevine Nexus SVP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Widevine-Nexus-SVP.git;protocol=https;branch=master"
SRCREV = "0fcb06456444f5516b5affeb9fe5e98786a36dc7"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
