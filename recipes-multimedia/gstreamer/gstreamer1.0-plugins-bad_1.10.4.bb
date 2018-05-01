require gstreamer1.0-plugins-bad.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=73a5855a8119deb017f5f13cf327095d \
                    file://COPYING.LIB;md5=21682e4e8fea52413fd26c60acb907e5 \
                    file://gst/tta/crc32.h;beginline=12;endline=29;md5=27db269c575d1e5317fffca2d33b3b50 \
                    file://gst/tta/filters.h;beginline=12;endline=29;md5=8a08270656f2f8ad7bb3655b83138e5a"

SRC_URI += " \
    https://gstreamer.freedesktop.org/src/gst-plugins-bad/gst-plugins-bad-${PV}.tar.xz \
    file://0001-mssdemux-improved-live-playback-support.patch \
    file://0002-mssdemux-Handle-the-adapter-in-the-subclass-after-bu.patch \
    file://0003-adaptivedemux-minimal-HTTP-context-support.patch \
    file://0004-Fix-to-set-current_fragment-for-live-streaming.patch \
    file://0005-mpdparser-MS-PlayReady-ContentProtection-parsing.patch \
    file://0006-Workaround-mss-live-streams-fragments.patch \
    file://0007-smoothstreaming-implement-adaptivedemux-s-get_live_s.patch \
    file://0008-mssdemux-Fix-fragment-parsing-issue-during-video-rep.patch \
    file://0009-Fix-HLS-live-stream-issues-with-http-cdn.metrologica.patch \
"

SRC_URI[md5sum] = "2757103e57a096a1a05b3ab85b8381af"
SRC_URI[sha256sum] = "23ddae506b3a223b94869a0d3eea3e9a12e847f94d2d0e0b97102ce13ecd6966"

S = "${WORKDIR}/gst-plugins-bad-${PV}"

EXTRA_OECONF += "WAYLAND_PROTOCOLS_SYSROOT_DIR=${RECIPE_SYSROOT}"

