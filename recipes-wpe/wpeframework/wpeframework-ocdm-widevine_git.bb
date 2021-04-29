SUMMARY = "WPE Framework OpenCDMi module for widevine"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1384d4b11dad572771bc2dad384029a6"

require include/wpeframework-plugins.inc

DEPENDS += " widevine"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Widevine.git;protocol=https;branch=development/widevine-15.3.0"

SRCREV = "795ae7c85489ad5e24eafd666a6adf07ef5a76ad"

FILES_${PN} = "\
    ${datadir}/WPEFramework/OCDM/*.drm \
"
