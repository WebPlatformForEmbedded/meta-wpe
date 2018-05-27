IMAGE_INSTALL_append = "\
   packagegroup-wpewebkit \
   packagegroup-wpeframework \
"

# If WPE Framework is enabled as distro feature, remove the default packagegroup-core-boot and run with our own
IMAGE_INSTALL_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'wpeframework', 'packagegroup-wpe-boot', '', d)}"
IMAGE_INSTALL_remove = "${@bb.utils.contains('DISTRO_FEATURES', 'wpeframework', 'packagegroup-core-boot', '', d)}"

IMAGE_INSTALL_rpi += "wpebackend-rdk"
