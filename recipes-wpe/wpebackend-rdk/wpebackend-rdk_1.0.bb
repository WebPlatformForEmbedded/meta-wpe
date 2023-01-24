require wpebackend-rdk.inc
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://src/wayland/display.h;;beginline=5;endline=24;md5=b1c8cb6b0857048a21b33611f01c575a"

PV = "1.0+git${SRCPV}"
SRC_URI += "\
    file://0001-libinput-Add-missing-xkbcommon-xkbcommon.h-header.patch \
    file://0002-cmake-Fix-FindWPE-module-use-imported-target.patch \
"
SRCREV ?= "6083f2b58f6a6e43ed4d51b1489b4d55425bda29"

