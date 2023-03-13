HOMEPAGE = "https://github.com/rdkcentral/ThunderTools"
SUMMARY = "Host/Native tooling for the WPEFramework/Thunder"
DESCRIPTION = "Host/Native tooling (i.e generators) required to build Thunder Framework"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c3349dc67b2f8c28fc99b300eb39e3cc"

RECIPE_BRANCH ?= "master"
SRC_URI[md5sum] = "42b518b9ccd6852d1d709749bc96cb70"
SRC_URI[sha256sum] = "f3c45b121cf6257eafabdc3a8008763aed1cd7da06dbabc59a9e4d2a5e4e6697"
SRC_URI = "git://github.com/rdkcentral/ThunderTools.git;protocol=git;branch=${RECIPE_BRANCH};protocol=https"
SRCREV ?= "13a2c71dec3cfab814a41b6a86dd8ea09ee17698"
PR = "r0"
S = "${WORKDIR}/git"

DEPENDS_append = " python3-native python3-jsonref-native"

PV = "3.0+git${SRCPV}"
OECMAKE_SOURCEPATH = "${WORKDIR}/git"

inherit cmake pkgconfig native python3native

FILES_${PN} += "${datadir}/*/Modules/*.cmake"

