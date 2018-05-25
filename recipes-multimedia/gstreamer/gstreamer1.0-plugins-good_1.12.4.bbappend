
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0001-souphttpsrc-cookie-jar-and-context-query-support.patch \
    file://0002-qtdemux-add-context-for-a-preferred-protection.patch \
    file://0003-qtdemux-dont-check-pushbased-edts.patch \
    file://0004-PATCH-qtdemux-also-push-buffers-without-encryption-i.patch \
    file://0005-qtdemux-fix-assert-when-moof-contains-one-sample.patch \
    file://0010-matroskademux-Allow-Matroska-headers-to-be-read-more.patch \
    file://0011-matroskademux-Start-stream-time-at-zero.patch \
    file://0012-matroskademux-emit-no-more-pads-when-the-Tracks-elem.patch \
"
