SUMMARY = "WPE Framework OpenCDMi module for widevine"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins widevine"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Widevine.git;protocol=ssh;branch=master"
SRCREV = "c9f15b5911dbe0ffbca6f54f97eb591e94efbaca"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
