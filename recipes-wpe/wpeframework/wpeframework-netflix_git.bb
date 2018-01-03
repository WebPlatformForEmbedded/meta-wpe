SUMMARY = "WPE Framework Netflix plugin"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "CLOSED"

DEPENDS = "wpeframework netflix libprovision"

PV = "3.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/WPEPluginNetflix.git;protocol=ssh;branch=master \
		   file://0001-cmake-Remove-redundant-include.patch"
SRCREV = "aa813b32befb90bfcde822cd0c24c0d7876e31e7"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[debug]            = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN}-dbg = "${libdir}/wpeframework/plugins/.debug/* ${libdir}/src/debug/*"
FILES_${PN} += "${libdir}/wpeframework/plugins/*.so"

# ----------------------------------------------------------------------------

RDEPENDS_${PN} = "wpeframework-dialserver wpeframework-provisioning"

TOOLCHAIN = "gcc"
