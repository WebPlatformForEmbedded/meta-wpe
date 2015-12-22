
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0002-mssdemux-PlayReady-WRM-parsing-support.patch \
    file://0003-adaptivedemux-improved-bitrate-estimations.patch \
    file://0004-mssdemux-improved-live-playback-support.patch \
    file://0006-daptivedemux-minimal-HTTP-context-support.patch \
    file://0007-optimize-gl-rpi-wip.patch \
"
