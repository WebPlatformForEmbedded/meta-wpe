SUMMARY = "WPE Framework Provisioning plugin"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "CLOSED"

DEPENDS = "wpeframework libprovision"

PV = "3.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/WPEPluginProvisioning.git;protocol=ssh;branch=master \
		  file://0002-cmake-Remove-redundant-include.patch"

SRCREV = "8645c167a0d1715b066ee27b185e1b227bdcba5a"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[debug]            = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/libWPEFrameworkProvisioning.so"
INSANE_SKIP_${PN} = ""

# ----------------------------------------------------------------------------

TOOLCHAIN = "gcc"
