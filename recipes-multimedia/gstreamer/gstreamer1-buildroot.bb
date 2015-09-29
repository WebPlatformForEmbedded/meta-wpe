LICENSE = "GPL"

SRC_URI = "file://${HOME}/Projects/yoct_projs/buildroot/gstreamer1-buildroot.tar;unpack=true"

DEPENDS += "bzip2 virtual/libgles2 virtual/egl"

LIC_FILES_CHKSUM = "file://COPYING;md5=6762ed442b3822387a51c92d928ead0d"
S = "${WORKDIR}/gstreamer1-buildroot"

inherit autotools pkgconfig gettext

