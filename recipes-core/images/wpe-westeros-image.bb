# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "WPE-On-Westeros compositor image for RaspberryPi"
LICENSE = "MIT"

include recipes-core/images/core-image-minimal.bb

require wpe-image.inc

inherit distro_features_check

REQUIRED_DISTRO_FEATURES = "wayland"

SPLASH_rpi = "psplash-raspberrypi"

IMAGE_FEATURES += "hwcodecs \
                   package-management \
                   ssh-server-dropbear \
                   splash \
"

IMAGE_INSTALL += "kernel-modules \
                  packagegroup-westeros \
"
