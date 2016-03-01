SUMMARY = "Metrological's Qt Browser for Qt 4 and 5"
HOMEPAGE = "http://www.metrological.com/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f73069ee5fe10af114e5300a37d32d44"

DEPENDS = "qtbase qtwebkit"

SRCREV = "8baebf79e799d037b3a061f71aee9c59cf6d062e"
PV = "2.0.14+gitr${SRCPV}"

SRC_URI = "git://github.com/Metrological/qtbrowser.git;protocol=http"

S = "${WORKDIR}/git"

inherit qmake5

OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/qtbrowser ${D}${bindir}/
}
