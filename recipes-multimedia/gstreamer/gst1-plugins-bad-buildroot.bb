LICENSE = "GPL"

SRC_URI = "file://${HOME}/Projects/yoct_projs/buildroot/gst1-plugins-bad-buildroot.tar;unpack=true"

DEPENDS += "gst1-plugins-base-buildroot bzip2 virtual/libgles2 virtual/egl"

LIC_FILES_CHKSUM = "file://COPYING;md5=73a5855a8119deb017f5f13cf327095d"
S = "${WORKDIR}/gst1-plugins-bad-buildroot"

inherit autotools pkgconfig gettext

EXTRA_OECONF = " --enable-egl --enable-gles2 "






