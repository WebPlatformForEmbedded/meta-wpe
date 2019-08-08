LICENSE = "Apache-2.0"

PV = "1.0+git${SRCPV}"

SRC_URI = "${WESTEROS_URI}"
SRCREV = "${WESTEROS_SRCREV}"
WESTEROS_URI ?= "git://github.com/rdkcmf/westeros"
WESTEROS_SRCREV ?= "eb698b55ba5646ec6d7daff32bb71be94b57ddee"
LICENSE_LOCATION ?= "${S}/LICENSE"
LIC_FILES_CHKSUM = "file://${LICENSE_LOCATION};md5=77cf8957a4ffe1f1ae0d474a3d95a3ed"

export SCANNER_TOOL = "wayland-scanner"

acpaths = "-I cfg"

DEPENDS += "wayland-native \
            gstreamer1.0 \
            wayland \
            libxkbcommon \
           "
# depends on virtual/egl and wayland
REQUIRED_DISTRO_FEATURES = "opengl wayland"

