SUMMARY = "WPE's backend based on a freedesktop.org stack."
HOMEPAGE = "https://github.com/Igalia/WPEBackend-fdo"
SECTION = "wpe"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=1f62cef2e3645e3e74eb05fd389d7a66"

DEPENDS = "glib-2.0 libwpe libxkbcommon virtual/libgl wayland"

inherit cmake

PV = "1.0.0+git${SRCPV}"

SRC_URI = "git://github.com/Igalia/WPEBackend-fdo.git;protocol=git;branch=master"
SRCREV = "5a4b58c7d6a70068d13b8404a0c970b03a856119"


S = "${WORKDIR}/git"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/libWPEBackend-fdo-0.1.so"
