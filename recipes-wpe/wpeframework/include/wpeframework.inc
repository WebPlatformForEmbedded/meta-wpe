# WPE Framework common
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"

PV = "3.0+gitr${SRCPV}"

PACKAGECONFIG ??= ""
PACKAGECONFIG[debug]            = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"

S = "${WORKDIR}/git"

inherit cmake pkgconfig python3native

TOOLCHAIN = "gcc"

EXTRA_OECMAKE += "-DCMAKE_SYSROOT=${STAGING_DIR_HOST}"

# Yocto root is under /home/root
WPEFRAMEWORK_PERSISTENT_PATH = "/home/root/"
