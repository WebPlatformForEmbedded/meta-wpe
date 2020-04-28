# WPE Framework common
HOMEPAGE = "https://github.com/rdkcentral"
SECTION = "wpe"

PV = "3.0+gitr${SRCPV}"

PACKAGECONFIG ??= ""
PACKAGECONFIG[debug]            = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

TOOLCHAIN = "gcc"

EXTRA_OECMAKE += "-DCMAKE_SYSROOT=${STAGING_DIR_HOST}"

# build RPI for -O2 (never a reason to build for -Os on RPIs)
#FULL_OPTIMIZATION_rpi_remove = "-Os"
#FULL_OPTIMIZATION_rpi_append = " -O2"

# Force BFD (to avoid gold)
LDFLAGS_append = " -fuse-ld=bfd"

# Yocto root is under /home/root
WPEFRAMEWORK_PERSISTENT_PATH = "/home/root/"
