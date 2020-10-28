SUMMARY = "WPEFramework interfaces"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f1dffbfd5c2eb52e0302eb6296cc3711"
PR = "r0"

require include/wpeframework.inc
DEPENDS = "wpeframework"

SRC_URI = "git://github.com/rdkcentral/ThunderInterfaces.git;protocol=git;branch=master"
SRCREV = "c9b00174618d507bed703f81fc50d3ddbd67c8eb"

# ----------------------------------------------------------------------------

PACKAGECONFIG ?= "release"

# ----------------------------------------------------------------------------

EXTRA_OECMAKE += " \
    -DBUILD_SHARED_LIBS=ON \
    -DBUILD_REFERENCE=${SRCREV} \
    -DPYTHON_EXECUTABLE=${STAGING_BINDIR_NATIVE}/python3-native/python3 \
"

# ----------------------------------------------------------------------------

do_install_append() {
    if ${@bb.utils.contains("PACKAGECONFIG", "opencdm", "true", "false", d)}
    then
        install -m 0644 ${D}${includedir}/WPEFramework/interfaces/IDRM.h ${D}${includedir}/cdmi.h
    fi
}

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/* ${datadir}/WPEFramework/* ${PKG_CONFIG_DIR}/*.pc"
FILES_${PN}-dev += "${libdir}/cmake/*"
FILES_${PN} += "${includedir}/cdmi.h"

INSANE_SKIP_${PN} += "dev-so"
INSANE_SKIP_${PN}-dbg += "dev-so"
