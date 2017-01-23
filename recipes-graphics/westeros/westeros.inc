LICENSE = "Apache-2.0"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "${WESTEROS_URI}"
SRCREV = "${WESTEROS_SRCREV}"
WESTEROS_URI ?= "git://github.com/rdkcmf/westeros"
WESTEROS_SRCREV ?= "7b624bbf3abd31db361b1df26bd1e1aac023ce4f"
LICENSE_LOCATION ?= "${S}/LICENSE"
LIC_FILES_CHKSUM = "file://${LICENSE_LOCATION};md5=703f52ef306fb8ef480744cc0da110fe"

export SCANNER_TOOL = "wayland-scanner"

acpaths = "-I cfg"

TOOLCHAIN = "gcc"

