require westeros.inc

SUMMARY = "This receipe compiles the westeros compositor component"

SRC_URI += "file://0001-Use-intptr_t-to-avoid-precision-errors-on-aarch64.patch \
           file://0002-Add_VCX_flags_support.patch \
           file://0003-Set-default-resolution-to-1080.patch \
           file://0004-Dispatch-custom-queue-instead-flushing-display.patch \
           "

PACKAGECONFIG ??= "incapp inctest increndergl incsbprotocol xdgv5"

PACKAGECONFIG_append = "${@bb.utils.contains("DISTRO_FEATURES", "x11", " x11", "", d)}"

PACKAGECONFIG[incapp] = "--enable-app=yes"
PACKAGECONFIG[inctest] = "--enable-test=yes"
PACKAGECONFIG[incplayer] = "--enable-player=yes"
PACKAGECONFIG[increndergl] = "--enable-rendergl=yes"
PACKAGECONFIG[incsbprotocol] = "--enable-sbprotocol=yes"
PACKAGECONFIG[xdgv4] = "--enable-xdgv4=yes"
PACKAGECONFIG[xdgv5] = "--enable-xdgv5=yes"
PACKAGECONFIG[x11] = ",,freeglut"

S = "${WORKDIR}/git"

WESTEROS_BACKEND ?= "westeros-soc-drm"
WESTEROS_BACKEND_rpi = "${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "westeros-soc-drm", "westeros-soc-rpi", d)}"

westeros-soc_imx = "westeros-soc-drm"

DEPENDS += "\
           westeros-simplebuffer \
           westeros-simpleshell \
           ${WESTEROS_BACKEND} \
          "

RDEPENDS_${PN} = "xkeyboard-config"

inherit autotools pkgconfig

SECURITY_CFLAGS_remove = "-fpie"
SECURITY_CFLAGS_remove = "-pie"

do_compile_prepend() {
   export SCANNER_TOOL=${STAGING_BINDIR_NATIVE}/wayland-scanner
   oe_runmake -C ${S}/protocol
}

do_install_append () {
   install -D -m 0644 ${S}/systemd/westeros-env ${D}${sysconfdir}/default/westeros-env
}

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"
INSANE_SKIP_${PN} += "dev-so"
