SUMMARY = "Metrological's CPP SDK abstraction layer"
SECTION = "metrological"
HOMEPAGE = "http://www.metrological.com/"
LICENSE = "Proprietary"

LIC_FILES_CHKSUM = "file://README.md;md5=21e48c57fe5a5f9881e15eb895867622"

DEPENDS = "zlib"

SRCREV = "ed436fa551441f2723faee776f1d55881e047109"

PV = "1.0"

SRC_URI = "git://git@github.com/Metrological/cppsdk.git;protocol=ssh;branch=cmake"

S = "${WORKDIR}/git"

EXTRA_OECMAKE="\
         -DINSTALL_HEADERS_TO_TARGET=ON \
         -DCPPSDK_DEBUG=OFF \
         -DCPPSDK_UNIT_TESTS=OFF \
         -DCPPSDK_GENERICS=ON  \
         -DCPPSDK_CRYPTALGO=ON \
         -DCPPSDK_WEBSOCKET=ON \
         -DCPPSDK_TRACING=ON \
         -DCPPSDK_DEVICES=ON"

inherit pkgconfig cmake

