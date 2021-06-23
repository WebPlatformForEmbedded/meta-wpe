FILESEXTRAPATHS_prepend := "${THISDIR}/linux-yocto:"

SRC_URI += "file://xt-checksum.scc \
            file://ebtables.scc \
	    file://vswitch.scc \
	    file://lxc.scc \
            file://docker.scc \
	    "

KERNEL_FEATURES_append = " "
KERNEL_MODULE_AUTOLOAD += "nf_conntrack_ipv6 openvswitch"

