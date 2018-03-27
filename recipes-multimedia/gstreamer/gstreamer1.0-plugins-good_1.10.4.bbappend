
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
	file://0001-qtdemux-distinguish-TFDT-with-value-0-from-no-TFDT-a.patch \
    file://0005-souphttpsrc-cookie-jar-and-context-query-support.patch \
    file://0006-qtdemux-add-context-for-a-preferred-protection.patch \
    file://0007-qtdemux-dont-check-pushbased-edts.patch \
    file://0008-qtdemux-also-push-buffers-without-encryption-info-in.patch \
    file://0009-qtdemux-fix-assert-when-moof-contains-one-sample.patch \
    file://0010-matroskademux-Allow-Matroska-headers-to-be-read-more.patch \
    file://0011-matroskademux-Start-stream-time-at-zero.patch \
    file://0012-matroskademux-emit-no-more-pads-when-the-Tracks-elem.patch \
"
