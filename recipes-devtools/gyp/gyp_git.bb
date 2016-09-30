SUMMARY = "GYP is a Meta-Build system: a build system that generates other build systems"
HOMEPAGE = "https://gyp.gsrc.io/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab828cb8ce4c62ee82945a11247b6bbd"

PV = "0.1+git${SRCPV}"

SRC_URI = "git://chromium.googlesource.com/external/gyp.git;protocol=https"
SRCREV = "702ac58e477214c635d9b541932e75a95d349352"

S = "${WORKDIR}/git"

inherit setuptools

BBCLASSEXTEND = "native"
