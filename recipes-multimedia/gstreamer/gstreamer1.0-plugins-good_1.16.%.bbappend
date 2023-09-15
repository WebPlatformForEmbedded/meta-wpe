FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI_append = " \
    file://0001-souphttpsrc-cookie-jar-and-context-query-support.patch \
    file://0002-0007-qtdemux-dont-check-pushbased-edts.patch.patch \
    file://0003-matroskademux-Start-stream-time-at-zero.patch \
    file://0004-matroskademux-emit-no-more-pads-when-the-Tracks-elem.patch \
    file://0005-Check-if-an-upstream-demuxer-provided-a-default-kid.patch \
    file://0006-qtdemux-Allow-streams-with-no-specified-protection-s.patch \
    file://0007-qtdemux-Parse-VP-Codec-Configuration-Box.patch \
    file://0008-qtdemux-added-support-for-cbcs-encryption-scheme.patch \
    file://0009-qtdemux-No-need-for-new-application-x-cbcs-caps.patch \
    file://0010-qtdemux-Clear-protection-events-when-we-get-new-ones.patch \
    file://0011-qtdemux-Add-support-for-cenc-sample-grouping.patch \
    file://0012-qtdemux-Don-t-emit-GstSegment-correcting-start-time-.patch \
    file://0013-qtdemux-Add-MSE-style-flush.patch \
    file://0014-qtdemux-Fix-crash-on-MSE-style-flush.patch \
"
APPLY_WPE_2.22_BUILD ??= "false"
WPEWEBKIT_2.22_PATCHES = " \
    file://0016-Manual-revert-of-bfd0e022-qtdemux-rework-segment-eve.patch \
"
APPLY_WPE_2.28_BUILD ??= "false"
WPEWEBKIT_2.28_PATCHES = " \
    file://0015-Revert-0007-qtdemux-dont-check-pushbased-edts.patch.patch \
"

SRC_URI_append = " ${@bb.utils.contains("PREFERRED_VERSION_wpewebkit", "2.28%", "${WPEWEBKIT_2.28_PATCHES}", "", d)}"
PACKAGECONFIG[matroska] = "-Dmatroska=enabled,-Dmatroska=disabled,"

PACKAGECONFIG_remove = "gtk"
