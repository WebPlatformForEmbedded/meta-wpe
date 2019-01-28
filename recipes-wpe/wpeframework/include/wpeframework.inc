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

# don't build debug if debug is not in packageconfig
FULL_OPTIMIZATION_remove = "${@bb.utils.contains('PACKAGECONFIG', 'debug', '-g', '', d)}"
