FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-omx-1.16:"

# in upstream meta-raspberrypi but not needed?
SRC_URI_remove = "file://0004-Properly-handle-drain-requests-while-flushing.patch"
SRC_URI_remove = "file://0005-Don-t-abort-gst_omx_video_dec_set_format-if-there-s-.patch"

SRC_URI += "\
    file://0003-omxvideodec-fix-deadlock-on-downstream-EOS.patch \
    file://0004-Enable-video-decoder-when-the-output-buffer-pool-is-.patch \
    file://0005-Manual-revert-of-0603e44-omxvideodec-enc-delay-alloc.patch \
    file://0006-Manual-revert-of-64f7f78-omxvideodec-Fix-segment-see.patch \
    file://0007-Manual-revert-of-7b6be34-omxbufferpool-deallocate-OM.patch \
    file://0008-Improved-video-decoder-stopping-on-flushing.patch \
    file://0009-Don-t-abort-gst_omx_video_dec_disable-if-there-s-a-t.patch \
"

