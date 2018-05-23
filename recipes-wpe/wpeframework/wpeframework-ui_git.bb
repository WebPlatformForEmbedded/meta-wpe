SUMMARY = "WPE Framework User Interface"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

require wpeframework-plugins.inc

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/WPEFrameworkUI.git;protocol=ssh;branch=master"
SRCREV = "eb28ebcaae03738a74e7bd143055a936c00de2bb"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	rm -rf ${D}${datadir}/WPEFramework/Controller/UI/*
	mkdir -p ${D}${datadir}/WPEFramework/Controller/UI
	cp -r ${S}/build/* ${D}${datadir}/WPEFramework/Controller/UI
}

FILES_${PN} += "${datadir}/WPEFramework/Controller/UI/*"
