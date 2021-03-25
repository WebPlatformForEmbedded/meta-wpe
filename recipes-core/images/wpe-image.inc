IMAGE_INSTALL_append = "\
   packagegroup-wpewebkit \
   packagegroup-wpeframework \
   ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'packagegroup-westeros', '', d)} \
   ${@bb.utils.contains('DISTRO_FEATURES', 'plugins-rdk', 'packagegroup-wpeframework-plugins-rdk', 'packagegroup-wpeframework-rdkservices', d)} \
"

# If WPE Framework is enabled as distro feature, remove the default packagegroup-core-boot and run with our own
IMAGE_INSTALL_append = "${@bb.utils.contains('DISTRO_FEATURES', 'wpeframework', 'packagegroup-wpe-boot', '', d)}"
IMAGE_INSTALL_remove = "${@bb.utils.contains('DISTRO_FEATURES', 'wpeframework', 'packagegroup-core-boot', '', d)}"

# make sure the packagegroup-wpeframework-rdkservices is not available
IMAGE_INSTALL_remove = "${@bb.utils.contains('DISTRO_FEATURES', 'plugins-rdk', 'packagegroup-wpeframework-rdkservices', '', d)}"
