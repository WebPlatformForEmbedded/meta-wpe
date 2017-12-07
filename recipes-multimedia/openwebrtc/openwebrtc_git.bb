DESCRIPTION = "A flexible cross-platform WebRTC client framework based on GStreamer."
HOMEPAGE = "http://www.openwebrtc.io/"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7df5db26808350824fa9e5e8b0236749"
SECTION = "devel"

DEPENDS = "gstreamer1.0-openwebrtc gstreamer1.0-plugins-bad libnice orc pulseaudio"

SRCREV = "008a47b1c4315a5efc183403d66bae883e567cc4"
SRC_URI = "git://github.com/EricssonResearch/openwebrtc.git \
           file://0000-remove-docs.patch \
           file://0001-Fix-build-with-gcc-7.patch \
          "

S = "${WORKDIR}/git"

PACKAGECONFIG ?= "owrgst disablebridge disableintrospection disabletests \
                 "

PACKAGECONFIG[owrgst] = "--enable-owr-gst,,"
PACKAGECONFIG[disablebridge] = "--disable-bridge,,"
PACKAGECONFIG[disableintrospection] = "--disable-introspection,,"
PACKAGECONFIG[disabletests] = "--disable-tests,,"

inherit autotools pkgconfig
