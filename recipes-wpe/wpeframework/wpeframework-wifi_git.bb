SUMMARY = "WPE Framework WiFi Proof-of-Concept plugin"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "CLOSED"

DEPENDS = "wpeframework"

PV = "3.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/WPEPluginsPOC.git;protocol=ssh;branch=WPEPluginWifi \
		  file://0002-cmake-Remove-redundant-include.patch"

SRCREV = "92618b16644b9653513e1be5d24278c94706623f"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[debug]            = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/libWifiScanClient.so ${libdir}/wpeframework/plugins/libWPEFrameworkWifiSetup.so*"
INSANE_SKIP_${PN} = "dev-so"

# ----------------------------------------------------------------------------

TOOLCHAIN = "gcc"
