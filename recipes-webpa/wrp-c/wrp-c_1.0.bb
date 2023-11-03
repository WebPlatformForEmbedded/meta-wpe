SUMMARY = "C library for Web Routing Protocol (WRP)"
HOMEPAGE = "https://github.com/Comcast/wrp-c"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

DEPENDS_append = " trower-base64 msgpack-c cimplog"

SRCREV = "71f8a39fe39f98da007ed4cdabbb192be1da1685"
SRC_URI = "git://github.com/xmidt-org/wrp-c.git"
PV = "git+${SRCPV}"

S = "${WORKDIR}/git"

inherit pkgconfig cmake

EXTRA_OECMAKE_append = " -DBUILD_TESTING=OFF -DBUILD_YOCTO=true"

LDFLAGS_append = " -lcimplog -lmsgpackc -ltrower-base64"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

