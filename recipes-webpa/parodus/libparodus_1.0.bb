SUMMARY = "C implementation of the WebPA client coordinator"
HOMEPAGE = "https://github.com/Comcast/parodus"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

DEPENDS_append = " cjson nopoll wrp-c wdmp-c trower-base64 nanomsg msgpack-c"

SRCREV = "8263bb06c8c16dc114c800a3d29d0c8252f15619"
SRC_URI = "git://github.com/Comcast/libparodus.git"
PV = "git+${SRCPV}"

S = "${WORKDIR}/git"

inherit pkgconfig cmake

CFLAGS_append = " \
    -I${STAGING_INCDIR} \
    -I${STAGING_INCDIR}/cjson \
    -I${STAGING_INCDIR}/nopoll \
    -I${STAGING_INCDIR}/wdmp-c \
    -I${STAGING_INCDIR}/wrp-c \
    -I${STAGING_INCDIR}/nanomsg \
    -I${STAGING_INCDIR}/trower-base64 \
    "

EXTRA_OECMAKE_append = " -DBUILD_TESTING=OFF -DBUILD_YOCTO=true"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

