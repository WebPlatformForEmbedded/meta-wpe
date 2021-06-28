SUMMARY = "WPE Framework OpenCDMi module for widevine"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1384d4b11dad572771bc2dad384029a6"

require include/wpeframework-plugins.inc

DEPENDS_append = " widevine"

PV = "3.0+gitr${SRCPV}"
SRC_URI = "git://git@github.com/rdkcentral/OCDM-Widevine.git;protocol=https;branch=development/widevine-15.3.0"
SRCREV = "70b3acf9af4fa74631d1eb071596534b02bae206"

FILES_${PN} = "${datadir}/WPEFramework/OCDM/*.drm"

