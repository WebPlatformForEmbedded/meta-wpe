# Overwrite the egl.pc and glesv2.pc from bcm-refsw as they do not contain the correct flags for wpebackend-rdk to detect nxclient or platform server
# to switch to platform server, set bcm-platform-server in your distro features (and make sure bcm-refsw is setup accordingly)

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://egl.pc \
    file://glesv2.pc \
"

do_install_append() {
    # only copy egl.pc if bcm-platform-server is not in the distro features
    if ${@bb.utils.contains("DISTRO_FEATURES", "bcm-platform-server", "false", "true", d)}
    then
        install -D -m 0644 ${WORKDIR}/egl.pc    ${D}${libdir}/pkgconfig/egl.pc
        install -D -m 0644 ${WORKDIR}/glesv2.pc ${D}${libdir}/pkgconfig/glesv2.pc
    fi
}
