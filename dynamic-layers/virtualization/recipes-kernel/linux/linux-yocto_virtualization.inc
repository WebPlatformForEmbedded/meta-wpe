FILESEXTRAPATHS:prepend := "${THISDIR}/linux-yocto:"

SRC_URI:append = " \
    file://xt-checksum.scc \
    file://ebtables.scc \
    file://vswitch.scc \
    file://lxc.scc \
    file://docker.scc \
"

KERNEL_FEATURES:append = " "
KERNEL_MODULE_AUTOLOAD += "nf_conntrack_ipv6 openvswitch"

