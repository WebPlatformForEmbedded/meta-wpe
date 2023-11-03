SUMMARY = "Simple logging library for rdklogger"
HOMEPAGE = "https://github.com/Comcast/cimplog"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRCREV = "4d96629b8e04230549cd2bee36bc4419a6e5aecc"
SRC_URI = " \
    git://github.com/xmidt-org/cimplog.git \
    file://0001-adjust-log-level-to-avoid-unnecessary-logs.patch \
"
PV = "git+${SRCPV}"

S = "${WORKDIR}/git"
inherit pkgconfig cmake

CIMP_LOG_LEVEL ??= "3"
EXTRA_OECMAKE_append = "\
    -DBUILD_TESTING=OFF \
    -DBUILD_YOCTO=true \
    -DLEVEL_DEFAULT=${CIMP_LOG_LEVEL} \
"

