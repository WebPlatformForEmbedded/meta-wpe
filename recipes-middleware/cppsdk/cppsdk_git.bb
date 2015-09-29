SUMMARY = "Metrological's CPP SDK abstraction layer"
SECTION = "metrological"
HOMEPAGE = "http://www.metrological.com/"
LICENSE = "Proprietary"

LIC_FILES_CHKSUM = "file://README.md;md5=21e48c57fe5a5f9881e15eb895867622"

DEPENDS = "zlib"

SRCREV = "391a3e13a7a08e6ccb1e8d5c6ec589e23ff8e9be"

PV = "1.0-r${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/cppsdk.git;protocol=ssh;branch=cmake"

S = "${WORKDIR}/git"

EXTRA_OECMAKE=""

inherit pkgconfig cmake

