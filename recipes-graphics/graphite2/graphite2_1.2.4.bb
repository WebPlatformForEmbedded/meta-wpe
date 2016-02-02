# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Font rendering capabilities for complex non-Roman writing systems"
HOMEPAGE = "http://sourceforge.net/projects/silgraphite/"
LICENSE = "LGPLv2+|GPLv2+|MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2d5025d4aa3495befef8f17206a5b0a1"
SECTION = "devel"
DEPENDS = "freetype"

SRC_URI = "${SOURCEFORGE_MIRROR}/silgraphite/${P}.tgz"
SRC_URI[md5sum] = "2ef839348fe28e3b923bf8cced440227"
SRC_URI[sha256sum] = "4bc3d5168029bcc0aa00eb2c973269d29407be2796ff56f9c80e10736bd8b003"

inherit gettext cmake
