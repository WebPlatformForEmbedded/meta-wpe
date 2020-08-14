SUMMARY = "WPE Framework OpenCDMi module for Widevine Nexus SVP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=feac6454ca1bb4ff09e7bc76d34f57ed"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw protobuf"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Widevine-Nexus-SVP.git;protocol=https;branch=master"
# Revision hash of R1 release
SRCREV = "9d9abe25db1b20e421c3b17f45f4c62a8d86d44a"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
