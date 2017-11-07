SUMMARY = "WPE Framework User Interface"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "CLOSED"

DEPENDS = "wpeframework"

PV = "3.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/WPEFrameworkUI.git;protocol=ssh;branch=master"
SRCREV = "611c35315b4baaea5c6a1da8322e0b3a13c16ff6"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	rm -rf ${D}${datadir}/WPEFramework/Controller/UI/*
	mkdir -p ${D}${datadir}/WPEFramework/Controller/UI
	cp -r ${S}/build/* ${D}${datadir}/WPEFramework/Controller/UI
}

FILES_${PN} += "${datadir}/WPEFramework/Controller/UI/*"
