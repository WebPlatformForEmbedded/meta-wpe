HOMEPAGE = "https://github.com/rdkcmf/westeros"
LICENSE = "Apache-2.0"
LICENSE_LOCATION ??= "${S}/LICENSE"
LIC_FILES_CHKSUM = "file://${LICENSE_LOCATION};md5=77cf8957a4ffe1f1ae0d474a3d95a3ed"

DEPENDS:append = " \
    gstreamer1.0 \
    libxkbcommon \
    wayland \
    wayland-native \
"

PV = "1.0+git${SRCPV}"
SRC_URI = "${WESTEROS_URI}"
SRCREV = "${WESTEROS_SRCREV}"
WESTEROS_URI ??= "git://github.com/rdkcmf/westeros;protocol=https"
WESTEROS_SRCREV ??= "fbd296bd25c7c9b122519569bcb5eb71ebebde14"

export SCANNER_TOOL = "wayland-scanner"

acpaths = "-I cfg"

# depends on virtual/egl and wayland
REQUIRED_DISTRO_FEATURES = "opengl wayland"

