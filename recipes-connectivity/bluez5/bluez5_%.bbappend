FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SYSTEMD_SERVICE_${PN}_remove = "${@bb.utils.contains('DISTRO_FEATURES', "bluetoothcontrol", "bluetooth.service", "", d)}"
SYSTEMD_SERVICE_${PN}_remove = "${@bb.utils.contains('DISTRO_FEATURES', "bluetoothcontrol", "brcm43438.service", "", d)}"

SRC_URI += " \
    file://0005-add-mgmt-header-also-into-the-header-list.patch \
    "
do_configure_append() {
    cd ${S}
    patch -p1 < ${WORKDIR}/0005-add-mgmt-header-also-into-the-header-list.patch
    cd -
}

do_install_append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', "bluetoothcontrol", "true", "false", d)}
    then
        if [ -f ${D}${systemd_unitdir} ]
        then
            rm -rf ${D}${systemd_unitdir}
        fi
        if [ -f ${D}${INIT_D_DIR}/bluetooth ]
        then
            rm -rf ${D}${INIT_D_DIR}/bluetooth
        fi
    fi
}
