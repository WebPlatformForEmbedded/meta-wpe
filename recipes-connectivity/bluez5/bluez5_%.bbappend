FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://0005-add-mgmt-header-also-into-the-header-list.patch \
    "
do_configure_append() {
    cd ${S}
    patch -p1 < ${WORKDIR}/0005-add-mgmt-header-also-into-the-header-list.patch
    cd -
}
