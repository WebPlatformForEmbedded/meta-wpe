SUMMARY = "WPE Framework User Interface"
DESCRIPTION = "WPE Framework User Interface web app"
HOMEPAGE = "https://github.com/rdkcentral/ThunderUI"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=66fe57b27abb01505f399ce4405cfea0"

require include/wpeframework-plugins.inc

PV = "3.0+gitr${SRCPV}"
RECIPE_BRANCH ?= "R4"

SRC_URI = "git://github.com/rdkcentral/ThunderUI.git;branch=${RECIPE_BRANCH};protocol=https"
SRCREV ?= "b4f54fd06fc4e6c748f21d3f62d3f8763f58d6d1"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    rm -rf ${D}${datadir}/WPEFramework/Controller/UI/*
    mkdir -p ${D}${datadir}/WPEFramework/Controller/UI
    cp -r ${S}/src/* ${D}${datadir}/WPEFramework/Controller/UI
    cp -r ${S}/dist/* ${D}${datadir}/WPEFramework/Controller/UI
}

FILES_${PN} += "${datadir}/WPEFramework/Controller/UI/*"

