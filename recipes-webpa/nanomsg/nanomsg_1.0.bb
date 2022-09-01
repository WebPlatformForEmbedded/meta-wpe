SUMMARY = "Socket library that provides several common communication patterns"
HOMEPAGE = "http://nanomsg.org/"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=587b3fd7fd291e418ff4d2b8f3904755"

SRCREV = "1.1.4"
SRC_URI = "\
    git://github.com/nanomsg/nanomsg.git \
    file://nanomsg-size.patch \
"

PV = "git+${SRCPV}"
S = "${WORKDIR}/git"

inherit pkgconfig cmake
