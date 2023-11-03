SUMMARY = "CCSP CcspCrSsp component"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspCr"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS_append = " ccsp-common-library dbus telemetry libunpriv rbus libxml2 utopia"

require ccsp_common.inc

RECIPE_BRANCH ?= "rdk-next"
SRC_URI = "\
    git://code.rdkcentral.com/r/rdkb/components/opensource/ccsp/CcspCr.git;protocol=https;branch=${RECIPE_BRANCH} \
    file://0001-ccspcr-build-support.patch \
    file://ccspcr-init \
"

SRCREV = "7054041df8817aa0e019a41363520ffeff9dcf91"
PV = "git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools systemd update-rc.d

EXTRA_OECONF_append = " --with-rbus-build=only"
CFLAGS_append = " \
    -DDISABLE_RDK_LOGGER \
    -I=${includedir}/dbus-1.0 \
    -I=${libdir}/dbus-1.0/include \
    -I=${includedir}/ccsp \
    -I${STAGING_INCDIR}/syscfg \
    -I${STAGING_INCDIR}/utapi \
    -I${STAGING_INCDIR}/utctx \
    -I${STAGING_INCDIR}/ulog \
    -I${STAGING_INCDIR}/rbus \
    -I${STAGING_INCDIR}/rtmessage \
    -I${STAGING_INCDIR}/libxml2 \
"

LDFLAGS_append = " -ldbus-1 -ltelemetry_msgsender -lprivilege -lsyscfg -lcjson -lmsgpackc"

do_install_append () {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/ccspcr-init ${D}${sysconfdir}/init.d/ccspcr

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 644 ${WORKDIR}/ccspcr.service ${D}${systemd_unitdir}/system
    fi
}

PACKAGES =+ "${PN}-initscript"
INITSCRIPT_PACKAGES = "${PN}-initscript"
INITSCRIPT_NAME_${PN}-initscript = "ccspcr"
INITSCRIPT_PARAMS_${PN}-initscript = "defaults 35 25"
RRECOMMENDS_${PN} = "${PN}-initscript"

FILES_${PN}-initscript = "${sysconfdir}/init.d/ccspcr"
FILES_${PN} += " ${bindir}/CcspCrSsp"

