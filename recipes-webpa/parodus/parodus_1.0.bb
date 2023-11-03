SUMMARY = "parodus client library"
SECTION = "libs"
DESCRIPTION = "C client library for parodus"
HOMEPAGE = "https://github.com/Comcast/parodus"

DEPENDS_append = " cjson nopoll wrp-c wdmp-c trower-base64 nanomsg msgpack-c util-linux cjwt ucresolv curl"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRCREV="becacd899082f8c3eceacfa35f7475759060fc07"
SRC_URI = "\
    git://github.com/xmidt-org/parodus.git \
    file://0001-external-deps-removal.patch \
    file://0002-parodus-service-to-lib.patch \
    file://0003-disable-Werror-Wall.patch \
"

RDEPENDS_${PN} += "util-linux-uuidgen"
RDEPENDS_${PN}_append_dunfell = " bash"

PV = "git+${SRCPV}"
S = "${WORKDIR}/git"

LDFLAGS_append = " -lm -lcjson -lnopoll -lwrp-c -lwdmp-c -lmsgpackc -ltrower-base64 -luuid -lnanomsg -lcjwt -lucresolv -lresolv"

CFLAGS_append = " \
    -I${STAGING_INCDIR} \
    -I${STAGING_INCDIR}/cjson \
    -I${STAGING_INCDIR}/nopoll \
    -I${STAGING_INCDIR}/wdmp-c \
    -I${STAGING_INCDIR}/wrp-c \
    -I${STAGING_INCDIR}/cimplog \
    -I${STAGING_INCDIR}/nanomsg \
    -I${STAGING_INCDIR}/trower-base64 \
    -I${STAGING_INCDIR}/cjwt \
    -I${STAGING_INCDIR}/ucresolv \
    -DFEATURE_DNS_QUERY \
"

inherit pkgconfig cmake
EXTRA_OECMAKE_append = " -DBUILD_TESTING=OFF -DBUILD_YOCTO=true -DFEATURE_DNS_QUERY=true"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*"

