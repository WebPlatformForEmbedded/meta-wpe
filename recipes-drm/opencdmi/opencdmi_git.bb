SUMMARY = "Open Content Decryption Module CDMI."
HOMEPAGE = "https://www.fokus.fraunhofer.de/en"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea83f8bc099c40bde8c4f2441a6eb40b"

DEPENDS = "rpcbind opencdm openssl"
DEPENDS_append_libc-musl = " libtirpc"
CPPFLAGS_append_libc-musl = " -I${STAGING_INCDIR}/tirpc"
CXXFLAGS_append_libc-musl = " -I${STAGING_INCDIR}/tirpc"
CFLAGS_append_libc-musl = " -I${STAGING_INCDIR}/tirpc"
LDFLAGS_append_libc-musl = " -ltirpc"

SRCREV = "c3b1dfa024f2e9f8f9f2384449ac9d1e3c324719"
PV = "1.0.gitr${SRCPV}"
S = "${WORKDIR}/git"

SRC_URI = "git://git@github.com/Metrological/open-content-decryption-module-cdmi.git;protocol=ssh;branch=opencdm"

do_install() {
	install -d ${D}${libdir}
	install -m 0755 ${B}/libocdmi.so ${D}${libdir}
}

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"
