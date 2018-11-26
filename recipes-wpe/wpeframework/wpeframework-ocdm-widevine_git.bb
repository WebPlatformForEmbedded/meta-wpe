SUMMARY = "WPE Framework OpenCDMi module for widevine"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

require wpeframework-plugins.inc

DEPENDS += " wpeframework-plugins widevine"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Widevine.git;protocol=ssh;branch=master \
           file://0001-CMAKE-Find-WPEFramework-though-pkg-config.patch \
"

SRCREV = "b40da03deb03f464722b1cbd8747da5993a5799c"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
