LICENSE = "GPL"

SRC_URI = "file://${HOME}/Projects/yoct_projs/buildroot/gst1-plugins-good-buildroot.tar;unpack=true"

DEPENDS += "gst1-plugins-base-buildroot bzip2 virtual/libgles2 virtual/egl"

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"
S = "${WORKDIR}/gst1-plugins-good-buildroot"

inherit autotools pkgconfig gettext





