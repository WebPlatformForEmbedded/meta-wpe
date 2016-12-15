LICENSE = "Apache-2.0"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "${WESTEROS_URI}"
SRCREV = "${WESTEROS_SRCREV}"
WESTEROS_URI ?= "git://github.com/rdkcmf/westeros"
WESTEROS_SRCREV ?= "3597a2d36d325b623119f81f21dbca7960b2e1cb"
LICENSE_LOCATION ?= "${S}/LICENSE"
LIC_FILES_CHKSUM = "file://${LICENSE_LOCATION};md5=703f52ef306fb8ef480744cc0da110fe"

export SCANNER_TOOL = "wayland-scanner"

TOOLCHAIN = "gcc"

