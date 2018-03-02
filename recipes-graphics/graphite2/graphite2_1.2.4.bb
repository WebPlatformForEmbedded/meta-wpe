# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Font rendering capabilities for complex non-Roman writing systems"
HOMEPAGE = "http://sourceforge.net/projects/silgraphite/"
LICENSE = "LGPLv2+|GPLv2+|MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2d5025d4aa3495befef8f17206a5b0a1"
SECTION = "devel"
DEPENDS = "freetype"

SRC_URI = "${SOURCEFORGE_MIRROR}/silgraphite/${P}.tgz"
SRC_URI[md5sum] = "cb530d737c8f2d1023797cf0587b4e05"
SRC_URI[sha256sum] = "7a07d3f7cca5c0b38ca811984ef8da536da32932d68c1a6cce33ec2462b930bf"

inherit gettext cmake
