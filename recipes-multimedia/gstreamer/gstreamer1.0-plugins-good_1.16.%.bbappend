FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

PACKAGECONFIG_remove = "gtk"

SRC_URI += "file://0001-souphttpsrc-cookie-jar-and-context-query-support.patch \
    file://0002-0007-qtdemux-dont-check-pushbased-edts.patch.patch \
    file://0003-matroskademux-Start-stream-time-at-zero.patch \
    file://0004-matroskademux-emit-no-more-pads-when-the-Tracks-elem.patch \
    file://0005-Check-if-an-upstream-demuxer-provided-a-default-kid.patch \
    file://0006-Manual-revert-of-bfd0e022-qtdemux-rework-segment-eve.patch \
    file://0007-qtdemux-Allow-streams-with-no-specified-protection-s.patch \
    file://0008-qtdemux-Parse-VP-Codec-Configuration-Box.patch \
    file://0009-qtdemux-added-support-for-cbcs-encryption-scheme.patch "
