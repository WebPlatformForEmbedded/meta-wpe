SUMMARY = "WPE Framework DIAL Server plugin"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "CLOSED"

DEPENDS = "wpeframework"

PV = "3.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/WPEPluginDIAL.git;protocol=ssh;branch=master"

SRCREV = "ee2908af4d242a692a60f0500a2af7bf63ad8880"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[debug]            = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/*.so"
FILES_${PN}-dbg += "${libdir}/wpeframework/plugins/.debug/*.so"

# ----------------------------------------------------------------------------

TOOLCHAIN = "gcc"
