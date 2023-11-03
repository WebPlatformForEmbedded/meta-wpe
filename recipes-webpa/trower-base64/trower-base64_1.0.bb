SUMMARY = "C implementation of base64 encode/decode"
HOMEPAGE = "https://github.com/Comcast/trower-base64"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1e01b26bacfc2232046c90a330332b3"

SRCREV = "fbb9440ae2bc1118866baefcea7ff814f16613dd"
SRC_URI = "git://github.com/Comcast/trower-base64.git;branch=main"
PV = "git+${SRCPV}"

S = "${WORKDIR}/git"

inherit pkgconfig cmake

EXTRA_OECMAKE_append = " -DBUILD_TESTING=OFF"
