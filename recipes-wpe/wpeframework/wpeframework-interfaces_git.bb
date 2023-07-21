SUMMARY = "WPEFramework interfaces"
DESCRIPTION = "Thunder interfaces component"
HOMEPAGE = "https://github.com/rdkcentral/ThunderInterfaces"
SECTION = "wpe"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2f6c18f99faffa0e5d4ff478705c53f8"

require include/wpeframework.inc
DEPENDS_append = " wpeframework"

PR = "r0"
PV = "3.0+gitr${SRCPV}"
RECIPE_BRANCH ?= "master"

SRC_URI = "git://github.com/rdkcentral/ThunderInterfaces.git;protocol=git;branch=${RECIPE_BRANCH};protocol=https"
SRCREV ?= "000b25b3a1824e6ccdc36fbb209276c5f1f964fe"

inherit python3native

EXTRA_OECMAKE += "\
    -DBUILD_SHARED_LIBS=ON \
    -DBUILD_REFERENCE=${SRCREV} \
    -DPYTHON_EXECUTABLE=${PYTHON} \
"

do_install_append() {
    if ${@bb.utils.contains("DISTRO_FEATURES", "opencdm", "true", "false", d)}
    then
        install -m 0644 ${D}${includedir}/WPEFramework/interfaces/IDRM.h ${D}${includedir}/cdmi.h
    fi
}

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/* ${datadir}/WPEFramework/* ${PKG_CONFIG_DIR}/*.pc"
FILES_${PN}-dev += "${libdir}/cmake/*"
FILES_${PN} += "${includedir}/cdmi.h"

INSANE_SKIP_${PN} += "dev-so"
INSANE_SKIP_${PN}-dbg += "dev-so"

