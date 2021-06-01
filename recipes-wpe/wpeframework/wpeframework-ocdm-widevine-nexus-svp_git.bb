SUMMARY = "WPE Framework OpenCDMi module for Widevine Nexus SVP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=feac6454ca1bb4ff09e7bc76d34f57ed"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRCREV = "88ee2c16e82c6b826e492aa918e25f68e8a61bfb"
SRC_URI = "git://code.rdkcentral.com/r/soc/broadcom/components/rdkcentral/OCDM-Widevine-Nexus-SVP.git;protocol=https;branch=master"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
