SUMMARY = "AAMP ABR library"
DESCRIPTION = "ABR (Automatic BitRate) Library is an independent library for automatic bitrate switching.\
    ABR will start at a reasonable bitrate and will ramp up or down, depending on network conditions."
HOMEPAGE = "https://github.com/rdkcmf/rdk-aampabr"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "0.1.gitr${SRCPV}"

RECIPE_BRANCH ?= "stable2"
SRC_URI = "git://github.com/rdkcmf/rdk-aampabr.git;branch=${RECIPE_BRANCH};protocol=https"
SRCREV ?= "94628fea94ea26527c53a03c6245623017c6cfe9"

S = "${WORKDIR}/git"

inherit cmake

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"
FILES_${PN} += "${libdir}/lib*.so"

# Fixme, something is pointing to a non-symlink and that pulls in -dev packages
INSANE_SKIP_${PN} = "dev-deps"

