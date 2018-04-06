do_install_append_rpi() {
    cat >> ${D}${sysconfdir}/fstab <<EOF

# Mount boot directory
/dev/mmcblk0p1 /boot vfat defaults 0 0
/dev/mmcblk0p2 /home/root ext4 defaults 0 0
EOF
}
