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

DEPENDS = "wayland-native \
           gstreamer1.0 \
           wayland \
           libxkbcommon \
           westeros-simplebuffer \
           westeros-simpleshell \
           ${WESTEROS} \
          "

RDEPENDS_${PN} = "xkeyboard-config"

inherit autotools pkgconfig

SECURITY_CFLAGS_remove = "-fpie"
SECURITY_CFLAGS_remove = "-pie"

do_compile_prepend() {
   export SCANNER_TOOL=${STAGING_BINDIR_NATIVE}/wayland-scanner
   oe_runmake -C ${S}/protocol
}
