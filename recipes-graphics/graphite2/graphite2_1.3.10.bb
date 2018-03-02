# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Font rendering capabilities for complex non-Roman writing systems"
HOMEPAGE = "http://graphite.sil.org/"
LICENSE = "LGPLv2+|GPLv2+|MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2d5025d4aa3495befef8f17206a5b0a1"
SECTION = "devel"
DEPENDS = "freetype"

SRC_URI = "https://github.com/silnrsi/graphite/releases/download/${PV}/graphite2-${PV}.tgz"
SRC_URI[md5sum] = "b39d5ed21195f8b709bcee548c87e2b5"
SRC_URI[sha256sum] = "90fde3b2f9ea95d68ffb19278d07d9b8a7efa5ba0e413bebcea802ce05cda1ae"

inherit gettext cmake
