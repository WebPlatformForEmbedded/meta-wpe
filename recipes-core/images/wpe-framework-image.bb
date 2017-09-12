# Copyright (C) 2017 Wouter van Boesschoten <wouter@wouterlucas.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "WPE Framework image for rpi"
LICENSE = "MIT"

SPLASH_rpi = "psplash-raspberrypi"
WPE_BACKEND_rpi = "rpi"

include wpe-image.bb

IMAGE_INSTALL_remove = "wpelauncher"
IMAGE_INSTALL_append = "packagegroup-wpeframework"
