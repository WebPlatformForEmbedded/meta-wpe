SUMMARY = "WPEFramework Libraries"
DESCRIPTION = "Thunder libraries component"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded/ThunderLibraries"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=85bcfede74b96d9a58c6ea5d4b607e58"

require include/wpeframework-common.inc

DEPENDS_append = " wpeframework-tools-native wpeframework"

PV = "3.0+gitr${SRCPV}"
PR = "r1"
RECIPE_BRANCH ?= "R4"
SRC_URI = "git://git@github.com:/WebPlatformForEmbedded/ThunderLibraries.git;protocol=ssh;branch=${RECIPE_BRANCH}"
SRCREV ?= "72c559ddc31fdc75c256cec9d7014a84ccfffc3f"


PACKAGECONFIG ??= "\
    ${@bb.utils.contains('MACHINE_FEATURES', 'bluetooth', 'bluetooth', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'broadcast', 'broadcast', '', d)} \
"

PACKAGECONFIG[bluetooth] = "-DBLUETOOTH=ON,-DBLUETOOTH=OFF,bluez5"
PACKAGECONFIG[broadcast] = "-DBROADCAST=ON,-DBROADCAST=OFF,"
PACKAGECONFIG[broadcastsiparse] = "-DBROADCAST_SI_PARSING=ON,-DBROADCAST_SI_PARSING=OFF,"

EXTRA_OECMAKE += "\
    -DBUILD_SHARED_LIBS=ON \
    -DBUILD_REFERENCE=${SRCREV} \
"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so ${datadir}/WPEFramework/* ${PKG_CONFIG_DIR}/*.pc"
FILES_${PN}-dev += "${libdir}/cmake/*"

INSANE_SKIP_${PN} += "dev-so"
INSANE_SKIP_${PN}-dbg += "dev-so"
