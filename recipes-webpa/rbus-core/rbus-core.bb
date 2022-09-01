SUMMARY = "IPC bus powering RDK unified bus framework"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=075c59e772e98d304efd052108da3bd7"

DEPENDS_append = " rtmessage msgpack-c"
S = "${WORKDIR}/git"
RECIPE_BRANCH ?= "rdk-next"
SRC_URI = "\
    git://code.rdkcentral.com/r/components/opensource/rbuscore.git;protocol=https;branch=${RECIPE_BRANCH} \
    file://rbuscore-init \
    file://rbuscore.service \
"
SRCREV = "1ab589a0aaa70b10afb21fa135683fcbbb704964"

inherit cmake pkgconfig systemd update-rc.d

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/rbuscore-init ${D}${sysconfdir}/init.d/rbuscore

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 644 ${WORKDIR}/rbuscore.service ${D}${systemd_unitdir}/system
    fi
}

PACKAGES =+ "${PN}-initscript"
INITSCRIPT_PACKAGES = "${PN}-initscript"
INITSCRIPT_NAME_${PN}-initscript = "rbuscore"
INITSCRIPT_PARAMS_${PN}-initscript = "defaults 30 24"
RRECOMMENDS_${PN} = "${PN}-initscript"

FILES_${PN}-initscript = "${sysconfdir}/init.d/rbuscore"

