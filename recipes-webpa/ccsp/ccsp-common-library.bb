SUMMARY = "CCSP libccsp_common component"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspCommonLibrary"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=19774cd4dd519f099bc404798ceeab19"

DEPENDS_append = " dbus openssl rbus rbus-core zlib"

require ccsp_common.inc

RECIPE_BRANCH ?= "rdk-next"
SRC_URI = "\
    git://github.com/rdkcmf/rdkb-CcspCommonLibrary.git;protocol=https;branch=${RECIPE_BRANCH} \
    file://0001-DBusLoop-SSL_state-TLS_ST_OK.patch \
    file://0001-SSLeay_add_all_algorithms-remove-in-openssl-1.1.patch \
    file://0003-support-to-build-for-non-rdk-platform.patch \
"

SRCREV = "6432e257aa30bdf766db16a0db83394801bc4708"
PV = "git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools systemd pkgconfig

SECURITY_STRINGFORMAT = ""

CFLAGS_append += " \
    -DSAFEC_DUMMY_API \
    -D_GNU_SOURCE -D__USE_XOPEN \
    -I${STAGING_INCDIR}/dbus-1.0 \
    -I${STAGING_LIBDIR}/dbus-1.0/include \
    -I${STAGING_INCDIR}/rbus \
    -I${STAGING_INCDIR}/rtmessage \
"

LDFLAGS_append = " -ldbus-1 -lrbus-core -lrtMessage -lrbus"

do_install_append_class-target () {
    install -d ${D}/usr/include/ccsp
    install -d ${D}/usr/include/ccsp/linux
    install -m 644 ${S}/source/debug_api/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/util_api/ansc/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/util_api/asn.1/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/util_api/http/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/util_api/slap/components/SlapVarConverter/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/util_api/stun/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/util_api/tls/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/util_api/web/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/cosa/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/cosa/package/slap/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/cosa/package/system/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/cosa/include/linux/*.h ${D}/usr/include/ccsp/linux
    install -m 644 ${S}/source/cosa/include/linux/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/ccsp/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/ccsp/custom/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/ccsp/components/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/ccsp/components/common/MessageBusHelper/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/ccsp/components/common/PoamIrepFolder/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/dm_pack/dm_pack_create_func.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/dm_pack/dm_pack_xml_helper.h ${D}/usr/include/ccsp

    # Config files
    install -d ${D}/usr/ccsp
    install -m 644 ${S}/config/ccsp_msg.cfg ${D}/usr/ccsp
}

FILES_${PN} += " /usr/ccsp ${libdir}"

