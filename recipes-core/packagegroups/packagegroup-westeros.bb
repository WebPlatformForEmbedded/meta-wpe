# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Westeros packages"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-westeros \
"

RDEPENDS_packagegroup-westeros = "\
    westeros \
    westeros-simplebuffer \
    westeros-simpleshell \
    westeros-sink \
    westeros-soc \
    ${WESTEROS_RENDERER} \
"

WESTEROS_RENDERER ?= ""

WESTEROS_RENDERER_rpi = "\
    westeros-render-dispmanx \
"
