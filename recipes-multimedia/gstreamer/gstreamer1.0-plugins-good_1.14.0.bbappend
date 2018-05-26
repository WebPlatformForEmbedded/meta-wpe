
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

PACKAGECONFIG_remove = "gtk"

SRC_URI += " \
    file://0001-souphttpsrc-cookie-jar-and-context-query-support.patch \
    file://0002-qtdemux-add-context-for-a-preferred-protection.patch \
    file://0003-qtdemux-also-push-buffers-without-encryption-info-in.patch \
"
