LICENSE = "GPL"

SRC_URI = "file://${HOME}/Projects/yoct_projs/buildroot/gst1-plugins-base-buildroot.tar;unpack=true"

DEPENDS += "gstreamer1-buildroot"

LIC_FILES_CHKSUM = "file://COPYING;md5=c54ce9345727175ff66d17b67ff51f58"
S = "${WORKDIR}/gst1-plugins-base-buildroot"

do_compile_prepend() {
	# HACK: for some strange reason this package expects this directory to exist
	mkdir -p /home/sander/Projects/emulator2/build-qemux86hyb/tmp/sysroots/qemux86hyb/home/sander/Projects/emulator2/build-qemux86hyb/tmp/sysroots/qemux86hyb/usr/include/freetype2
}

inherit autotools pkgconfig gettext




