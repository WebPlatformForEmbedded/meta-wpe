DESCRIPTION = "GStreamer plugin for OpenWebRTC."
HOMEPAGE = "http://www.openwebrtc.io/"
LICENSE = "BSD2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=ffcf846341f3856d79a483eafa18e2a5"
SECTION = "lib"

SRC_URI = "git://git@github.com/sctplab/usrsctp.git;protocol=ssh"
SRCREV = "d6c555f1df8f206bebbdbff75912fd88f346f8aa"

S = "${WORKDIR}/git"

PACKAGECONFIG ?= "disablewarnings"
PACKAGECONFIG[disablewarnings] = "--disable-warnings-as-errors,,"

do_configure_prepend() {
    mkdir -p ${S}/m4
    touch ${S}/AUTHORS
    touch ${S}/ChangeLog
    touch ${S}/NEWS
    touch ${S}/README
}

inherit autotools pkgconfig
