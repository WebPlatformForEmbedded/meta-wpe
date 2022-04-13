SUMMARY = "WPE Backend for freedesktop.org stack"
DESCRIPTION = "WPE's backend based on a freedesktop.org stack."
HOMEPAGE = "https://github.com/Igalia/WPEBackend-fdo"
SECTION = "wpe"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=1f62cef2e3645e3e74eb05fd389d7a66"

DEPENDS:append = " glib-2.0 libwpe libxkbcommon virtual/libgl wayland"

RECIPE_BRANCH ?= "master"
PV = "1.0.0+git${SRCPV}"
SRC_URI = "git://github.com/Igalia/WPEBackend-fdo.git;branch=${RECIPE_BRANCH};protocol=https"
SRCREV ?= "5a4b58c7d6a70068d13b8404a0c970b03a856119"
S = "${WORKDIR}/git"

inherit cmake

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/libWPEBackend-fdo-0.1.so"

INSANE_SKIP:${PN} = "dev-so"
