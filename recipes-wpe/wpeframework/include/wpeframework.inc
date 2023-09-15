# Common for thunder and modules which depends thunder
S = "${WORKDIR}/git"

inherit cmake pkgconfig

TOOLCHAIN = "gcc"
EXTRA_OECMAKE += "-DCMAKE_SYSROOT=${STAGING_DIR_HOST}"
EXTRA_OECMAKE += "-DTOOLS_SYSROOT=${STAGING_DIR_NATIVE}"

# Force BFD (to avoid gold)
LDFLAGS_append = " -fuse-ld=bfd"

# Yocto root is under /home/root
WPEFRAMEWORK_PERSISTENT_PATH ??= "/home/root"
WPEFRAMEWORK_VOLATILE_PATH ??= ""
WPEFRAMEWORK_DATA_PATH ??= ""
WPEFRAMEWORK_INSTALL_PATH ??= ""

WPEFRAMEWORK_GROUP ??= ""
WPEFRAMEWORK_OCDM_GROUP ??= ""
WPEFRAMEWORK_SUPER_USER_GROUP ??= "root"
WPEFRAMEWORK_PLATFORM_VIDEO_SUBSYSTEM ??= ""
WPEFRAMEWORK_PLATFORM_VIDEO_DEVICE_GROUP ??= "video"

DEPENDS += "git-replacement-native wpeframework-tools-native"

