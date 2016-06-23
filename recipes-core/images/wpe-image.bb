# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "WPE initramfs rootfs image"
LICENSE = "MIT"

IMAGE_FEATURES += " \
    ssh-server-openssh \
    package-management \
"
include recipes-core/images/rpi-basic-image.bb
require wpe-image.inc
