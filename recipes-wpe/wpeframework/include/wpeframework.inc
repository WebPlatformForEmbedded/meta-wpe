# Common for thunder and tools
SECTION = "wpe"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f1dffbfd5c2eb52e0302eb6296cc3711"
HOMEPAGE = "https://github.com/rdkcentral/Thunder"

SRC_URI[md5sum] = "42b518b9ccd6852d1d709749bc96cb70"
SRC_URI[sha256sum] = "f3c45b121cf6257eafabdc3a8008763aed1cd7da06dbabc59a9e4d2a5e4e6697"
SRC_URI = "git://github.com/rdkcentral/Thunder.git;protocol=git;branch=master"
SRCREV = "3e7c6f09af0a495ca51caa564370fb2c4f23c39e"

PR = "r0"
PV = "3.0+git${SRCPV}"
S = "${WORKDIR}/git"
