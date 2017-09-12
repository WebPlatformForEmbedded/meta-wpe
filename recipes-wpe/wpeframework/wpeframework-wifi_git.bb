SUMMARY = "WPE Framework WiFi Proof-of-Concept plugin"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "CLOSED"

DEPENDS = "wpeframework"

PV = "3.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/WPEPluginsPOC.git;protocol=ssh;branch=WPEPluginWifi \
		  file://0002-cmake-Remove-redundant-include.patch"

SRCREV = "1c853144c5f464bed43739cf9ad1f32e725d7951"

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
