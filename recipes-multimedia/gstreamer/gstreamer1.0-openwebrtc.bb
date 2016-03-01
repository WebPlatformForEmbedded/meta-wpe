DESCRIPTION = "GStreamer plugin for OpenWebRTC."
HOMEPAGE = "http://www.openwebrtc.io/"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34e05c5cfe87e7aec2dc32bd24ebdb33"
SECTION = "devel"

DEPENDS = "gstreamer1.0-plugins-base usrsctp"

SRC_URI = "git://git@github.com/EricssonResearch/openwebrtc-gst-plugins.git;protocol=ssh"
SRCREV = "f40f3302007da00f0bfb82065d705b62c2ea1afd"

S = "${WORKDIR}/git"

FILES_${PN} = "${libdir}/gstreamer-1.0/* \
               ${libdir}/libgstsctp-1.0.so.*"

FILES_${PN}-dbg += "${libdir}/gstreamer-1.0/.debug"

inherit autotools pkgconfig
