include westeros.inc

SUMMARY = "This receipe compiles the westeros compositor component"

PACKAGECONFIG ??= "incapp inctest increndergl incsbprotocol xdgv4"

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

DEPENDS = "wayland-native \
           gstreamer1.0 \
           wayland \
           libxkbcommon \
           westeros-simplebuffer \
           westeros-simpleshell \
           ${WESTEROS} \
          "

RDEPENDS_${PN} = "xkeyboard-config"

inherit autotools pkgconfig systemd

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
   fi
   install -D -m 0755 ${S}/systemd/westeros-init ${D}${bindir}/westeros-init
}

SYSTEMD_SERVICE_${PN} = "westeros.service"

