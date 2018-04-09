SUMMARY = "WPE Framework Spotify Proof-of-Concept plugin"
LICENSE = "CLOSED"
DEPENDS += " alsa-lib"

require wpeframework-plugins.inc

SRC_URI = "git://git@github.com/Metrological/WPEPluginsSpotify.git;protocol=ssh;branch=master \
          file://0001-cmake-Remove-redundant-include.patch"

SRCREV = "cf5368e41273906b4297af483a5a244e441a4637"
