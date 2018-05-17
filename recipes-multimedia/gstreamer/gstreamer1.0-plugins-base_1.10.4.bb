require gstreamer1.0-plugins-base.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=c54ce9345727175ff66d17b67ff51f58 \
                    file://COPYING.LIB;md5=6762ed442b3822387a51c92d928ead0d \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607"

SRC_URI = "http://gstreamer.freedesktop.org/src/gst-plugins-base/gst-plugins-base-1.10.4.tar.xz \
           file://0001-Makefile.am-don-t-hardcode-libtool-name-when-running.patch \
           file://0002-Makefile.am-prefix-calls-to-pkg-config-with-PKG_CONF.patch \
           file://0003-riff-add-missing-include-directories-when-calling-in.patch \
           file://0004-rtsp-drop-incorrect-reference-to-gstreamer-sdp-in-Ma.patch \
           file://0005-Revert-uridecodebin-Use-the-correct-caps-name-for-MS.patch \
           file://0006-ssaparse-enhance-SSA-text-lines-parsing.patch \
           file://0007-subparse-set-need_segment-after-sink-pad-received-GS.patch \
           file://0008-typefind-Expand-the-search-range-for-HLS-detection.patch \
           file://0009-encodebin-Need-more-buffers-in-output-queue-for-bett.patch \
           file://0010-make-gio_unix_2_0-dependency-configurable.patch \
           file://0011-get-caps-from-src-pad-when-query-caps.patch \
           "

SRC_URI[md5sum] = "f6b46f8fac01eb773d556e3efc369e86"
SRC_URI[sha256sum] = "f6d245b6b3d4cb733f81ebb021074c525ece83db0c10e932794b339b8d935eb7"

S = "${WORKDIR}/gst-plugins-base-${PV}"
