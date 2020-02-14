SUMMARY = "Universal cryptography abstraction layer"
HOMEPAGE = "https://github.com/Metrological/cryptography"
SECTION = "wpe"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fe8768cbb5fd322f7d50656133549de"
PR = "r0"

DEPENDS = " \
    wpeframework  \
"

PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git"

SRC_URI = "git://git@github.com/Metrological/cryptography.git;protocol=ssh;branch=master"

SRCREV = "346c1ae6be00a1275f5f5baf27b19165abe49782"

inherit cmake pkgconfig

PACKAGECONFIG ?= " \
    openssl \
"

PACKAGECONFIG[openssl] = '-DCRYPTOGAPHY_IMPLENTATION=OpenSSL,,openssl'
PACKAGECONFIG[nexus] = '-DCRYPTOGAPHY_IMPLENTATION=Nexus,,broadcom-refsw'

FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev += "${libdir}/cmake/* ${PKG_CONFIG_DIR}/*.pc"

EXTRA_OECMAKE += "-DCMAKE_SYSROOT=${STAGING_DIR_HOST}"
