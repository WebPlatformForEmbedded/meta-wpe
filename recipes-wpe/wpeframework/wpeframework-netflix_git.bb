SUMMARY = "WPE Framework Netflix plugin"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "CLOSED"

DEPENDS = "wpeframework netflix libprovision (>= 2.0)"

PV = "3.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/WPEPluginNetflix.git;protocol=ssh;branch=master \
		   file://0001-cmake-Remove-redundant-include.patch"
SRCREV = "3185d44931d81ef5034ddfc2cf197fc73e0b3617"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[debug]            = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"

RDEPENDS_${PN} = "wpeframework-dialserver wpeframework-provisioning"

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/*.so"
# ----------------------------------------------------------------------------

TOOLCHAIN = "gcc"
