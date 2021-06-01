# Common for thunder and modules which depends thunder
HOMEPAGE = "https://github.com/rdkcentral"
SECTION = "wpe"

S = "${WORKDIR}/git"
PV = "3.0+gitr${SRCPV}"

inherit cmake pkgconfig

TOOLCHAIN = "gcc"
EXTRA_OECMAKE += "-DCMAKE_SYSROOT=${STAGING_DIR_HOST}"

# Force BFD (to avoid gold)
LDFLAGS_append = " -fuse-ld=bfd"

# Yocto root is under /home/root
WPEFRAMEWORK_PERSISTENT_PATH ??= "/home/root/"

