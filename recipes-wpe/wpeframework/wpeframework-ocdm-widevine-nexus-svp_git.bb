SUMMARY = "WPE Framework OpenCDMi module for Widevine Nexus SVP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=feac6454ca1bb4ff09e7bc76d34f57ed"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw protobuf"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Widevine-Nexus-SVP.git;protocol=https;branch=master"
# Revision hash of R1 release
SRCREV = "f3d2f2aa8028f0023bf734bc617ab93d63b5fc78"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
