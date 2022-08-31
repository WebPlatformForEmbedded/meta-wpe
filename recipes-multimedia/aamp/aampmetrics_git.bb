SUMMARY = "AAMP Metris library"
DESCRIPTION = "Metrics Library is an independent library for session statistics generation/reporting in JSON format"
HOMEPAGE = "https://github.com/rdkcmf/rdk-aampmetrics"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

DEPENDS_append = " cjson"

PV = "0.1.gitr${SRCPV}"

SRC_URI = "git://github.com/rdkcmf/rdk-aampmetrics.git;branch=stable2;protocol=https"
SRCREV = "c6b9de63f2a8755db59ee300c02c6c89e392dad5"

S = "${WORKDIR}/git"

inherit cmake

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"
FILES_${PN} += "${libdir}/lib*.so"

# Fixme, something is pointing to a non-symlink and that pulls in -dev packages
INSANE_SKIP_${PN} = "dev-deps"

