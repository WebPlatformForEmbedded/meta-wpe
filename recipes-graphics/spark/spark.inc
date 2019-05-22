SUMMARY = "Spark/pxCore is a small open source library that provides a portable framebuffer and windowing abstraction for C++"
HOMEPAGE = "http://www.sparkui.org/index.html"
SECTION = "spark"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=e7948fb185616891f6b4b35c09cd6ba5"

DEPENDS = "openssl"

PV = "1.7.0+git${SRCPV}"

SRC_URI = "git://github.com/pxscene/pxCore;protocol=git;branch=master"
SRCREV = "746382b7139bb7de4ebe357d766a4fbac243cab9"

S = "${WORKDIR}/git"
