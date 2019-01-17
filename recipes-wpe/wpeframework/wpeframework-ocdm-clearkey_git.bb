SUMMARY = "WPE Framework OpenCDMi module for clearkey"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require include/wpeframework-plugins.inc

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Clearkey.git;protocol=https;branch=master"
SRCREV = "3fb37d8ee90a8af941a6ebeff6309f89ead60fd4"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
