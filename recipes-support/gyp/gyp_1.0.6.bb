# Copyright (C) 2018 Metrological
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "GYP is a Meta-Build system: a build system that generates other build systems."
HOMEPAGE = "https://gyp.gsrc.io/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab828cb8ce4c62ee82945a11247b6bbd"
SECTION = "devel"

DEPENDS = "python-setuptools-native"

SRC_URI = "git://chromium.googlesource.com/external/gyp;protocol=https"
SRCREV = "81286d388abf5c8f946f3f4be274bd987a690952"
SRC_URI[md5sum] = "bff0072f2d8567cf491052b1ae7d6769"
SRC_URI[sha256sum] = "4f65901d7e2ea6cda50a31affbf2fc67d16aceda54cce3a04a33ccfb6a2e2aaf"

S = "${WORKDIR}/git"

inherit pythonnative

do_configure() {
	(cd ${B}; rm -rf build)
}

do_compile() {
	cd ${B};${PYTHON} setup.py build;
}

do_install() {
	install -d ${D}${libdir}/gyp
    cp -av --no-preserve=ownership ${B}/build/lib/gyp/* ${D}${libdir}/gyp/
}

BBCLASSEXTEND = "native"
