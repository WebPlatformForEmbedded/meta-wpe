# WPE Framework common
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"

PV = "3.0+gitr${SRCPV}"

PACKAGECONFIG ??= ""
PACKAGECONFIG[debug]            = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

TOOLCHAIN = "gcc"

EXTRA_OECMAKE += "-DCMAKE_SYSROOT=${STAGING_DIR_HOST}"
