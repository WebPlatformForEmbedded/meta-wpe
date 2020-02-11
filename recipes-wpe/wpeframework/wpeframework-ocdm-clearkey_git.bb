SUMMARY = "WPE Framework OpenCDMi module for clearkey"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"

require include/wpeframework-plugins.inc

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/OCDM-Clearkey.git;protocol=https;branch=master \
           file://0001-OpenSSL-align-implementation-with-latest-OpenSSL-1.1.patch \
           "
SRCREV = "30cd6f65e70f921c625f5fdb0e19444817579dbc"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"
