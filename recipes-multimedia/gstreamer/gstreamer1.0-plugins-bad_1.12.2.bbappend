
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0004-Fix-to-set-current_fragment-for-live-streaming.patch \
"

