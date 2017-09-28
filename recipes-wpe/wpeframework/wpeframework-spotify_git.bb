SUMMARY = "WPE Framework Spotify Proof-of-Concept plugin"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "CLOSED"

DEPENDS = "wpeframework alsa-lib"

PV = "3.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/WPEPluginsSpotify.git;protocol=ssh;branch=WPEPluginSpotify \
		  file://0001-cmake-Remove-redundant-include.patch"

SRCREV = "5616ca7a7001aa51ccc7de63ec877e756d9e3120"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[debug]            = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/libWPEFrameworkSpotify.so"
INSANE_SKIP_${PN} = "dev-so already-stripped"

# ----------------------------------------------------------------------------

TOOLCHAIN = "gcc"
