SUMMARY = "WPEFramework Libraries"
DESCRIPTION = "Thunder libraries component"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded/ThunderLibraries"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=85bcfede74b96d9a58c6ea5d4b607e58"

require include/wpeframework-common.inc

DEPENDS_append = " wpeframework"

PV = "3.0+gitr${SRCPV}"
PR = "r1"
RECIPE_BRANCH ?= "main"
SRC_URI = "git://git@github.com:/WebPlatformForEmbedded/ThunderLibraries.git;protocol=ssh;branch=${RECIPE_BRANCH}"
SRCREV ?= "0e0d04168e85ab749e358b902ea45077067b1833"

#inherit python3native

PACKAGECONFIG ??= "\
    ${@bb.utils.contains('MACHINE_FEATURES', 'bluetooth', 'bluetooth', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'broadcast', 'broadcast', '', d)} \
"

PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_debug', 'debug', '', d)}"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_debugoptimized', 'debugoptimized', '', d)}"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_production', 'production', '', d)}"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_release', 'release', '', d)}"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_releasesymbols', 'releasesymbols', '', d)}"

# Buildtype
PACKAGECONFIG[debug] = "-DBUILD_TYPE=Debug,,"
PACKAGECONFIG[debugoptimized] = "-DBUILD_TYPE=DebugOptimized,,"
PACKAGECONFIG[releasesymbols] = "-DBUILD_TYPE=ReleaseSymbols,,"
PACKAGECONFIG[release] = "-DBUILD_TYPE=Release,,"
PACKAGECONFIG[production] = "-DBUILD_TYPE=Production,,"

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
