# Network and Timesync are handled through Thunder/WPEFramework
PACKAGECONFIG_remove = "${@bb.utils.contains('DISTRO_FEATURES', 'thunder', 'networkd timedated timesyncd resolved', '', d)}"

do_setup_arp_configs() {
    if [ "${@bb.utils.contains('DISTRO_FEATURES', 'thunder', 'true', '', d)}" ]; then
        cat >> ${D}${libdir}/sysctl.d/50-default.conf <<EOF

# arp settings
net.ipv4.conf.default.arp_ignore = 1
net.ipv4.conf.all.arp_ignore = 1

EOF
    fi
}

addtask do_setup_arp_configs after do_install before do_package
