LICENSE = "Apache-2.0"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "${WESTEROS_URI}"
SRCREV = "${WESTEROS_SRCREV}"
WESTEROS_URI ?= "git://github.com/rdkcmf/westeros"
WESTEROS_SRCREV ?= "058e64c5640d9fe3d3b39f82a3f935fe5e780768"
LICENSE_LOCATION ?= "${S}/LICENSE"
LIC_FILES_CHKSUM = "file://${LICENSE_LOCATION};md5=77cf8957a4ffe1f1ae0d474a3d95a3ed"

export SCANNER_TOOL = "wayland-scanner"

CXXFLAGS_append = " -std=c++11"

acpaths = "-I cfg"

DEPENDS += "wayland-native \
            gstreamer1.0 \
            wayland \
            libxkbcommon \
           "
# depends on virtual/egl and wayland
REQUIRED_DISTRO_FEATURES = "opengl wayland"
