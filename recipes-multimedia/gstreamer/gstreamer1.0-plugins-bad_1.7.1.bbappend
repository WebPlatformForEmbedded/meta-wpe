
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0001-mssdemux-improved-live-playback-support.patch \
    file://0002-adaptivedemux-minimal-HTTP-context-support.patch \
"
