SUMMARY = "Host/Native tooling for the Web Platform for Embedded Framework"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"

PV = "3.0+git${SRCPV}"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEFramework.git;protocol=git \
           file://0001-Changes-to-support-JSON-and-ProxyStub-generator-with-python3.patch \
"

SRC_URI[md5sum] = "42b518b9ccd6852d1d709749bc96cb70"
SRC_URI[sha256sum] = "f3c45b121cf6257eafabdc3a8008763aed1cd7da06dbabc59a9e4d2a5e4e6697"

SRCREV = "6cf39ada500c61f373acc40643aa33d6cfd7a6df"

inherit cmake pkgconfig native python3native

RDEPENDS_${PN} = "python3"

DEPENDS = "\
    python3-native \
    python3-jsonref-native \
"

OECMAKE_SOURCEPATH = "${WORKDIR}/git/Tools"

FILES_${PN}-dev += "${datadir}/*/Modules/*.cmake"
