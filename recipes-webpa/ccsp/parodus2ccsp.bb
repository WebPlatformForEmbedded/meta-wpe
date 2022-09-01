SUMMARY = "parodus2ccsp client library"
SECTION = "libs"
DESCRIPTION = "C client library for parodus2ccsp"
HOMEPAGE = "https://github.com/Comcast/parodus2ccsp"

DEPENDS_append = " cjson msgpack-c dbus ccsp-common-library trower-base64 cimplog wdmp-c nanomsg wrp-c libparodus libunpriv util-linux utopia"
RDEPENDS_${PN} = "cjson msgpack-c trower-base64 cimplog wdmp-c nanomsg wrp-c libparodus bash utopia"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRCREV = "2fdffe0b783cef4beb5ca0232b4def4d63c87c45"

RECIPE_BRANCH ?= "master"
SRC_URI = "\
    git://github.com/xmidt-org/parodus2ccsp.git;branch=${RECIPE_BRANCH} \
    file://0001-parodus2ccp-app-to-lib.patch \
    file://0002-build-for-non-rdk-platform.patch \
    file://0003-enable-sysconfig-for-non-rdk-build.patch \
"

PV = "git+${SRCPV}"
S = "${WORKDIR}/git"
require ccsp_common.inc

inherit pythonnative

CFLAGS_append = " \
	-I${STAGING_INCDIR}/ccsp \
	-I${STAGING_INCDIR}/wdmp-c \
	-I${STAGING_INCDIR}/libparodus \
	-I${STAGING_INCDIR}/dbus-1.0 \
	-I${STAGING_LIBDIR}/dbus-1.0/include \
	-I${STAGING_INCDIR}/cimplog \
        -I${STAGING_INCDIR}/trower-base64 \
	"

inherit pkgconfig cmake
EXTRA_OECMAKE_append = " -DBUILD_TESTING=OFF -DBUILD_YOCTO=true"

do_install_append() {
    install -d ${D}/usr/ccsp/webpa
    install -m 644 ${S}/source/arch/intel_usg/boards/rdkb_atom/config/comcast/WebpaAgent.xml ${D}/usr/ccsp/webpa
}

FILES_${PN} += " \
    ${exec_prefix}/ccsp/webpa \
    ${libdir}/*.so \
"
