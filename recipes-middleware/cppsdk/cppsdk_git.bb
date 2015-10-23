SUMMARY = "Metrological's CPP SDK abstraction layer"
HOMEPAGE = "http://www.metrological.com/"
SECTION = "metrological"
LICENSE = "CLOSED"

DEPENDS = "zlib"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/cppsdk.git;protocol=ssh"

SRCREV = "ed0ed767a6ebae4305acc974600f30d7637d42df"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

# Default features, can be overridden.
CPPSDK_FEATURES ??= "generics cryptalgo websocket tracing websocket devices"

EXTRA_OECMAKE= '\
    -DINSTALL_HEADERS_TO_TARGET=ON \
    ${@bb.utils.contains("CPPSDK_FEATURES", "debug", "-DCPPSDK_DEBUG=ON", "-DCPPSDK_DEBUG=OFF",d)} \
    ${@bb.utils.contains("CPPSDK_FEATURES", "unittests", "-DCPPSDK_UNIT_TESTS=ON", "-DCPPSDK_UNIT_TESTS=OFF",d)} \
    ${@bb.utils.contains("CPPSDK_FEATURES", "generics", "-DCPPSDK_GENERICS=ON", "-DCPPSDK_GENERICS=OFF",d)} \
    ${@bb.utils.contains("CPPSDK_FEATURES", "cryptalgo", "-DCPPSDK_CRYPTALGO=ON", "-DCPPSDK_CRYPTALGO=OFF",d)} \
    ${@bb.utils.contains("CPPSDK_FEATURES", "websocket", "-DCPPSDK_WEBSOCKET=ON", "-DCPPSDK_WEBSOCKET=OFF",d)} \
    ${@bb.utils.contains("CPPSDK_FEATURES", "tracing", "-DCPPSDK_TRACING=ON", "-DCPPSDK_TRACING=OFF",d)} \
    ${@bb.utils.contains("CPPSDK_FEATURES", "devices", "-DCPPSDK_DEVICES=ON", "-DCPPSDK_DEVICES=OFF",d)} \
'
