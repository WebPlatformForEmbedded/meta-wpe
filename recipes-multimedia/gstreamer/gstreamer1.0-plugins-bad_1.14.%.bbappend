FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI += " \
    file://0001-adaptivedemux-minimal-HTTP-context-support.patch \
    file://0002-Fix-to-set-current_fragment-for-live-streaming.patch \
    file://0003-Add-a-workaround-patch-for-the-mss-live-stream-fragm.patch \
    file://0004-mssdemux-Reduce-SmoothStreaming-latency-on-live-stre.patch \
    file://0006-mssdemux-Ignore-inactive-streams-to-compute-min-frag.patch \
    file://0008-smoothstreaming-add-support-for-absolute-fragment-ur.patch \
    file://0009-Fix-HLS-live-stream-issues-with-http-cdn.metrologica.patch \
    file://0010-Parse-playready-payload.patch \
    file://0011-mssdemux-support-for-live-content-as-vod.patch \
    file://0012-mssdemux-fix-protection-data-double-free.patch \
"
