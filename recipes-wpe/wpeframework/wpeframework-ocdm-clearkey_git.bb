SUMMARY = "WPE Framework OpenCDMi module for clearkey"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Clearkey.git;protocol=ssh;branch=master"
SRCREV = "297366e5d9f94929b3319b4c2212d90b934b845b"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
