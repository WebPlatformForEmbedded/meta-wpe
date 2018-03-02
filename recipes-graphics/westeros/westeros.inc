LICENSE = "Apache-2.0"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "${WESTEROS_URI}"
SRCREV = "${WESTEROS_SRCREV}"
#WESTEROS_URI ?= "git://github.com/rdkcmf/westeros"
#WESTEROS_SRCREV ?= "447572269e2b2db22747e495435938c61b0dbc73"
WESTEROS_URI ?= "git://github.com/Metrological/westeros"
WESTEROS_SRCREV ?= "1edcfb04d139f14a12231cf97d1c37338e97f515"
LICENSE_LOCATION ?= "${S}/LICENSE"
LIC_FILES_CHKSUM = "file://${LICENSE_LOCATION};md5=703f52ef306fb8ef480744cc0da110fe"

export SCANNER_TOOL = "wayland-scanner"

acpaths = "-I cfg"

DEPENDS += "wayland-native \
            gstreamer1.0 \
            wayland \
            libxkbcommon \
           "
# depends on virtual/egl and wayland
REQUIRED_DISTRO_FEATURES = "opengl wayland"

