DESCRIPTION = "WPE Framework image for rpi userland"
LICENSE = "MIT"

include recipes-core/images/core-image-minimal.bb

require wpe-image.inc

inherit distro_features_check

DISTRO_FEATURES_remove = "wayland"

SPLASH_rpi = "psplash-raspberrypi"

IMAGE_FEATURES += "hwcodecs \
                   package-management \
                   ssh-server-dropbear \
                   splash \
"

IMAGE_INSTALL += "kernel-modules \
                  packagegroup-wpeframework \
"
