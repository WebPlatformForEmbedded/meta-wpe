SUMMARY = "Open Content Decryption Module CDMI."
HOMEPAGE = "https://www.fokus.fraunhofer.de/en"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea83f8bc099c40bde8c4f2441a6eb40b"

DEPENDS = "rpcbind opencdm openssl"

SRCREV = "a739feb6111143abdc6e26fa587086bba89ded95"
PV = "1.0.gitr${SRCPV}"
S = "${WORKDIR}/git"

SRC_URI = "git://git@github.com/Metrological/open-content-decryption-module-cdmi.git;protocol=ssh;branch=opencdm"

do_install() {
	install -d ${D}${libdir}
	install -m 0755 ${B}/libocdmi.so ${D}${libdir}
}

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"
