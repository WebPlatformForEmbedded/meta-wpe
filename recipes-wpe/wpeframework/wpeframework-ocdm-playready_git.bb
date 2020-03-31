SUMMARY = "WPE Framework OpenCDMi module for playready"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require include/wpeframework-plugins.inc

DEPENDS += " playready"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Playready.git;protocol=https;branch=master"
SRCREV = "3a8672dfe33af2a68dcc4c3c0cf69326ad9bb01b"


FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
