
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0001-adaptivedemux-minimal-HTTP-context-support.patch \
    file://0002-Fix-to-set-current_fragment-for-live-streaming.patch \
    file://0003-Add-a-workaround-patch-for-the-mss-live-stream-fragm.patch \
"

