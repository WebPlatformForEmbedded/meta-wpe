SUMMARY = "rbus library component"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ed63516ecab9f06e324238dd2b259549"

DEPENDS_append = " rbus-core rtmessage linenoise"
S = "${WORKDIR}/git"
RECIPE_BRANCH ?= "rdk-next"
SRC_URI = "\
    git://code.rdkcentral.com/r/components/opensource/rbus.git;protocol=https;branch=${RECIPE_BRANCH} \
    file://rbus-init \
    file://rbus.service \
"
SRCREV = "0e79c34e5faae52f77e7a196c5eae7595c1cdca4"

inherit cmake pkgconfig systemd update-rc.d

CFLAGS_append = " -Wno-unused-result"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/rbus-init ${D}${sysconfdir}/init.d/rbus

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 644 ${WORKDIR}/rbus.service ${D}${systemd_unitdir}/system
    fi
}

PACKAGES =+ "${PN}-initscript"
INITSCRIPT_PACKAGES = "${PN}-initscript"
INITSCRIPT_NAME_${PN}-initscript = "rbus"
INITSCRIPT_PARAMS_${PN}-initscript = "defaults 25 24"
RRECOMMENDS_${PN} = "${PN}-initscript"

FILES_${PN}-initscript = "${sysconfdir}/init.d/rbus"

