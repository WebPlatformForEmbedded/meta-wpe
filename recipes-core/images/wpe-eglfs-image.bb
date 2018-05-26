DESCRIPTION = "WPE Framework image for WPE EGLFS with WPE Framework"
LICENSE = "MIT"

include recipes-core/images/core-image-minimal.bb

require wpe-image.inc

inherit distro_features_check

DISTRO_FEATURES_remove = "wayland"

IMAGE_FEATURES += "hwcodecs \
                   package-management \
                   ssh-server-dropbear \
"


# If WPE Framework is enabled as distro feature, remove the default packagegroup-core-boot and run with our own
IMAGE_INSTALL += " ${@bb.utils.contains('DISTRO_FEATURES', 'wpeframework', 'packagegroup-wpe-boot', '', d)} \
				   kernel-modules \
"

IMAGE_INSTALL_remove = "${@bb.utils.contains('DISTRO_FEATURES', 'wpeframework', 'packagegroup-core-boot', '', d)}"
