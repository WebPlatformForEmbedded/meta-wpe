include gstreamer1.0-omx.inc

LIC_FILES_CHKSUM = "\
    file://COPYING;md5=4fbd65380cdd255951079008b364516c \
    file://omx/gstomx.h;beginline=1;endline=21;md5=5c8e1fca32704488e76d2ba9ddfa935f \
"

SRC_URI = "\
    http://gstreamer.freedesktop.org/src/gst-omx/gst-omx-${PV}.tar.xz \
    file://0001-Don-t-try-to-acquire-buffer-when-src-pad-isn-t-activ.patch \
    file://0002-fix-decoder-flushing.patch \
    file://0003-no-timeout-on-get-state.patch \
    file://0005-Don-t-abort-gst_omx_video_dec_set_format-if-there-s-.patch \
    file://0006-omxvideodec-fix-deadlock-on-downstream-EOS.patch \
"

SRC_URI[md5sum] = "cedb230f1c47d0cf4b575d70dff66ff2"
SRC_URI[sha256sum] = "45072925cf262f0fd528fab78f0de52734e46a5a88aa802fae51c67c09c81aa2"

S = "${WORKDIR}/gst-omx-${PV}"
