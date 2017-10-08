
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0005-souphttpsrc-cookie-jar-and-context-query-support.patch \
    file://0006-qtdemux-add-context-for-a-preferred-protection.patch \
    file://0007-qtdemux-dont-check-pushbased-edts.patch \
    file://0008-qtdemux-also-push-buffers-without-encryption-info-in.patch \
"
