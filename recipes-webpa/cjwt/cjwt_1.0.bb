SUMMARY = "cjwt library"
DESCRIPTION = "Recipe to build c library for processing jwt token"
HOMEPAGE = "https://github.com/Comcast/cjwt"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

DEPENDS_append = " trower-base64 cjson openssl"

SRCREV = "51714cf769c046ca454ebdf0ae2bc542a3d8b376"
SRC_URI = "git://github.com/Comcast/cjwt.git"
PV = "git+${SRCPV}"
PR = "r1"
S = "${WORKDIR}/git"

LDFLAGS_append = " -lm -lcjson -ltrower-base64"

CFLAGS_append = "\
    -I${STAGING_INCDIR} \
    -I${STAGING_INCDIR}/cjson \
    -I${STAGING_INCDIR}/trower-base64 \
"

inherit cmake pkgconfig
EXTRA_OECMAKE_append = " -DBUILD_TESTING=OFF -DBUILD_YOCTO=true"

FILES_${PN} += "${libdir}/*"

FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} += "dev-so"
