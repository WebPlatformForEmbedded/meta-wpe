include westeros.inc

SUMMARY = "This receipe compiles the westeros compositor component"

SRC_URI += "file://0001-Use-intptr_t-to-avoid-precision-errors-on-aarch64.patch \
            file://0002-Set-exports-at-westeros-sysvinit.patch \
            file://0003-Add_VCX_flags_support.patch \
"

PACKAGECONFIG ??= "incapp inctest increndergl incsbprotocol xdgv5"

PACKAGECONFIG_append = "${@bb.utils.contains("DISTRO_FEATURES", "x11", " x11", "", d)}"

PACKAGECONFIG[incapp] = "--enable-app=yes"
PACKAGECONFIG[inctest] = "--enable-test=yes"
PACKAGECONFIG[inctest] = "--enable-test=yes"
PACKAGECONFIG[increndergl] = "--enable-rendergl=yes"
PACKAGECONFIG[incsbprotocol] = "--enable-sbprotocol=yes"
PACKAGECONFIG[xdgv4] = "--enable-xdgv4=yes"
PACKAGECONFIG[xdgv5] = "--enable-xdgv5=yes"
PACKAGECONFIG[x11] = ",,freeglut"

WESTEROS ?= "${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "", "westeros-soc", d)}"

S = "${WORKDIR}/git"

westeros-soc_hikey-32 = "westeros-soc-drm"

westeros-soc_dragonboard-410c-32 = "westeros-soc-drm"

westeros-soc_dragonboard-820c-32 = "westeros-soc-drm"

westeros-soc_poplar-32 = "westeros-soc-drm"

westeros-soc_imx8mqevk = "westeros-soc-drm"

DEPENDS += "\
           westeros-simplebuffer \
           westeros-simpleshell \
           ${WESTEROS} \
          "

RDEPENDS_${PN} = "xkeyboard-config"

inherit autotools pkgconfig systemd update-rc.d

SECURITY_CFLAGS_remove = "-fpie"
SECURITY_CFLAGS_remove = "-pie"

def is_drm_enabled(d):
    return bb.utils.contains('WESTEROS_BACKEND', 'westeros-soc-drm', '1', '0', d)

do_compile_prepend() {
   export SCANNER_TOOL=${STAGING_BINDIR_NATIVE}/wayland-scanner
   oe_runmake -C ${S}/protocol
}

do_install_append () {
    install -D -m 0644 ${S}/systemd/westeros-env ${D}${sysconfdir}/default/westeros-env

    if ${@bb.utils.contains("DISTRO_FEATURES", "compositor", "false", "true", d)}
    then
        install -D -m 0755 ${S}/systemd/westeros-init ${D}${bindir}/westeros-init

        if [ "${@bb.utils.contains("DISTRO_FEATURES", "systemd", "yes", "no", d)}" = "yes" ]; then
            install -D -m 0644 ${S}/systemd/westeros.service ${D}${systemd_unitdir}/system/westeros.service
        else
            install -D -m 0755 ${S}/systemd/westeros.sysvinit ${D}${sysconfdir}/init.d/westeros
        fi

        if [ -f ${D}${bindir}/westeros-init -a ${@is_drm_enabled(d)} -eq 1 ]; then
            sed -i -e '/--renderer/i export WESTEROS_DRM_MODE=1920x1080' ${D}${bindir}/westeros-init
            sed -i -e '/--renderer/i export LD_PRELOAD=/usr/lib/libwesteros_gl.so.0' ${D}${bindir}/westeros-init
            sed -i -e '/--renderer/ s#$# --module /usr/lib/libresolutionmodule.so#' ${D}${bindir}/westeros-init
        fi
        sed -i -e "s#/usr/lib#${libdir}#g" ${D}/${bindir}/westeros-init
    fi
}

INITSCRIPT_NAME = "westeros"
INITSCRIPT_PARAMS = "defaults"

SYSTEMD_SERVICE_${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'compositor', '', 'westeros.service' , d)}"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"
INSANE_SKIP_${PN} += "dev-so"
