SUMMARY = "WPE Framework OpenCDMi module for Widevine Nexus SVP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"

require include/wpeframework-plugins.inc

DEPENDS += " broadcom-refsw"

SRC_URI = "git://code.rdkcentral.com/r/soc/broadcom/components/rdkcentral/OCDM-Widevine-Nexus-SVP.git;protocol=http;branch=master"
SRCREV = "fa60a8b37af4da396a8ba108dc4f9f85b6eaf10e"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
