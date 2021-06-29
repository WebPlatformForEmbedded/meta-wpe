# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Westeros packages"
DESCRIPTION = "Westeros related packgroup"
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
    ${WESTEROS_SOC} \
    ${WESTEROS_RENDERER} \
"

WESTEROS_SOC ?= "westeros-soc-drm"

WESTEROS_RENDERER ?= ""

