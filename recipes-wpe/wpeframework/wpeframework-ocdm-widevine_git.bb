SUMMARY = "WPE Framework OpenCDMi module for widevine"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require include/wpeframework-plugins.inc

DEPENDS += " widevine"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Widevine.git;protocol=https;branch=master"
SRCREV = "052b138536bf209981a9360cc9ce1ae990a792ed"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
