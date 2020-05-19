FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append += " file://tinyxml.pc "

do_install_append() {
        install -Dm644 ${B}/../tinyxml.pc ${D}${libdir}/pkgconfig/tinyxml.pc
}
