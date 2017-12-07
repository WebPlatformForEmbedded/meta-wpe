SUMMARY = "WPE Framework Spotify Proof-of-Concept plugin"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "CLOSED"

DEPENDS = "wpeframework alsa-lib"

PV = "3.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/WPEPluginsSpotify.git;protocol=ssh;branch=master \
		  file://0001-cmake-Remove-redundant-include.patch"

SRCREV = "cf5368e41273906b4297af483a5a244e441a4637"

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
