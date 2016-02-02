# Copyright (c) 2012-2014 LG Electronics, Inc.

DESCRIPTION = "c-ares is a C library that resolves names asynchronously."
HOMEPAGE = "http://daniel.haxx.se/projects/c-ares/"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;beginline=17;endline=23;md5=d08205a43bc63c12cf394ac1d2cce7c3"

inherit autotools
inherit pkgconfig

SRC_URI = "http://c-ares.haxx.se/download/c-ares-${PV}.tar.gz"

S = "${WORKDIR}/c-ares-${PV}"
PR = "r1"

EXTRA_OECONF = "--enable-shared"
CFLAGS_remove = "-D_FORTIFY_SOURCE=2"

do_install_append() {
    install -d ${D}/${includedir}/ares
    install -m 0644 ares*.h ${D}/${includedir}/ares/
}

FILES_${PN}-dev += "${includedir}/ares/*.h"

SRC_URI[md5sum] = "1196067641411a75d3cbebe074fd36d8"
SRC_URI[sha256sum] = "3d701674615d1158e56a59aaede7891f2dde3da0f46a6d3c684e0ae70f52d3db"

