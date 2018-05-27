# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "WPE base image"
LICENSE = "MIT"

include recipes-core/images/core-image-minimal.bb

require wpe-image.inc

IMAGE_FEATURES += " \
    hwcodecs \
    ssh-server-dropbear \
    package-management \
"

IMAGE_INSTALL += "kernel-modules"