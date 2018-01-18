COMPATIBLE_MACHINE = "rpi"

do_install_append () {
    cat >> ${D}${sysconfdir}/fstab <<EOF

# Mount boot directory
/dev/mmcblk0p1 /boot vfat defaults 0 0
/dev/mmcblk0p2 /home/root ext4 defaults 0 0

EOF
}
