FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

PACKAGECONFIG_remove = "gtk"

SRC_URI += " \
    file://0001-souphttpsrc-cookie-jar-and-context-query-support.patch \
    file://0002-qtdemux-add-context-for-a-preferred-protection.patch \
    file://0003-0007-qtdemux-dont-check-pushbased-edts.patch.patch \
    file://0004-qtdemux-also-push-buffers-without-encryption-info-in.patch \
    file://0005-matroskademux-Start-stream-time-at-zero.patch \
    file://0006-matroskademux-emit-no-more-pads-when-the-Tracks-elem.patch \
    file://0007-Check-if-an-upstream-demuxer-provided-a-default-kid.patch \
    file://0008-qtdemux-Keep-sample-data-from-the-current-fragment-o.patch \
"
