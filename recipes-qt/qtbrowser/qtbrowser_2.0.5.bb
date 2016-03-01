SUMMARY = "Metrological's Qt Browser for Qt 4 and 5"
HOMEPAGE = "http://www.metrological.com/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f73069ee5fe10af114e5300a37d32d44"

DEPENDS = "qtbase qtwebkit"

SRCREV = "b4996deede1207daa0bbe7bf3b590e435528fef5"

SRC_URI = "git://github.com/Metrological/qtbrowser.git;protocol=http"
SRC_URI += "file://conditional_WebSecurityEnabled.patch"

S = "${WORKDIR}/git"

inherit qmake5

OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/qtbrowser ${D}${bindir}/
}
