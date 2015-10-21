SUMMARY = "Metrological's Qt Browser for Qt 4 and 5"
HOMEPAGE = "http://www.metrological.com/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f73069ee5fe10af114e5300a37d32d44"

DEPENDS = "qtbase qtwebkit"

SRCREV = "7193f983d5a07207a67dd22ec0ef0877b885e4fc"
PV = "2.0.11+gitr${SRCPV}"

SRC_URI = "git://github.com/Metrological/qtbrowser.git;protocol=http"
# SRC_URI += "file://remove_WebSecurityEnabled.patch"

S = "${WORKDIR}/git"

inherit qmake5

OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/qtbrowser ${D}${bindir}/
}

