SUMMARY = "WPE Framework OpenCDMi module for widevine"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"

inherit features_check
REQUIRED_DISTRO_FEATURES = "widevine"

require include/wpeframework-plugins.inc

DEPENDS += " widevine"

SRC_URI = "git://git@github.com/rdkcentral/OCDM-Widevine.git;protocol=https;branch=master"
SRCREV = "adb0c34ebe04ccdff54090de3c5614357bc1d858"

do_install_append() {
    install -m 755 -d ${D}/${sysconfdir}/WideVine
    install -m 755 ${S}/tools/keybox/testkeybox.bin ${D}/${sysconfdir}/WideVine/keybox.bin
}

FILES_${PN} = " \
    ${datadir}/WPEFramework/OCDM/*.drm \
    ${sysconfdir}/WideVine/* \
"