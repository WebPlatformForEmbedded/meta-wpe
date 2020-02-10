FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://001-provisioning_hook.patch \
    file://002-package_name_code_path_crash_fix.patch \
    file://003-fix_crash_when_opkg_does_reinit.patch \
"

PACKAGECONFIG[libopkg]   = "--enable-libopkg-api,--disable-libopkg-api"
PACKAGECONFIG[provision] = "--enable-provision,--disable-provision"

PACKAGECONFIG_append = " libopkg "
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'provisioning', 'provision', '', d)} "


do_install_append () {
        install -d ${D}${includedir}/libopkg
        install -m 0644 ${S}/libopkg/*.h ${D}${includedir}/libopkg
}
