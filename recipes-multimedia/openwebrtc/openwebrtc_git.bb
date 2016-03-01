DESCRIPTION = "A flexible cross-platform WebRTC client framework based on GStreamer."
HOMEPAGE = "http://www.openwebrtc.io/"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7df5db26808350824fa9e5e8b0236749"
SECTION = "devel"

DEPENDS = "gstreamer1.0-openwebrtc libnice orc pulseaudio"

SRC_URI = "git://git@github.com/EricssonResearch/openwebrtc.git;protocol=ssh"
SRCREV = "30ca989ff4237ebe17641e15fa9cb17c98bf11f9"
SRC_URI += "file://0000-remove-docs.patch"

S = "${WORKDIR}/git"

PACKAGECONFIG ?= "owrgst disablebridge disableintrospection disabletests disablegtkdoc"

PACKAGECONFIG[owrgst] = "--enable-owr-gst,,"
PACKAGECONFIG[disablebridge] = "--disable-bridge,,"
PACKAGECONFIG[disableintrospection] = "--disable-introspection,,"
PACKAGECONFIG[disabletests] = "--disable-tests,,"
PACKAGECONFIG[disablegtkdoc] = "--disable-gtk-doc,,"

inherit autotools pkgconfig
