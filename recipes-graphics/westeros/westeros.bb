include westeros.inc

SUMMARY = "This receipe compiles the westeros compositor component"

SRC_URI += "file://0001-Use-intptr_t-to-avoid-precision-errors-on-aarch64.patch \
	    file://0002-Set-exports-at-westeros-sysvinit.patch"

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

S = "${WORKDIR}/git"

WESTEROS_BACKEND ?= "westeros-soc-drm"
WESTEROS_BACKEND_rpi = "${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "westeros-soc-drm", "westeros-soc-rpi", d)}"

DEPENDS += "\
           westeros-simplebuffer \
           westeros-simpleshell \
           ${WESTEROS_BACKEND} \
          "

RDEPENDS_${PN} = "xkeyboard-config"

inherit autotools pkgconfig systemd update-rc.d

SECURITY_CFLAGS_remove = "-fpie"
SECURITY_CFLAGS_remove = "-pie"

do_compile_prepend() {
   export SCANNER_TOOL=${STAGING_BINDIR_NATIVE}/wayland-scanner
   oe_runmake -C ${S}/protocol
}

do_install_append () {
   install -D -m 0644 ${S}/systemd/westeros-env ${D}${sysconfdir}/default/westeros-env
   if [ "${@bb.utils.contains("DISTRO_FEATURES", "systemd", "yes", "no", d)}" = "yes" ]; then
       install -D -m 0644 ${S}/systemd/westeros.service ${D}${systemd_unitdir}/system/westeros.service
   else
       install -D -m 0755 ${S}/systemd/westeros.sysvinit ${D}${sysconfdir}/init.d/westeros
   fi
   install -D -m 0755 ${S}/systemd/westeros-init ${D}${bindir}/westeros-init
}

INITSCRIPT_NAME = "westeros"
INITSCRIPT_PARAMS = "defaults"

SYSTEMD_SERVICE_${PN} = "westeros.service"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"
INSANE_SKIP_${PN} += "dev-so"
