include westeros.inc

SUMMARY = "This receipe compiles the westeros compositor simple-shelk component"

S = "${WORKDIR}/git"
LICENSE_LOCATION = "${S}/LICENSE"

DEPENDS += "wayland wayland-native glib-2.0"

inherit autotools pkgconfig

SECURITY_CFLAGS_remove = "-fpie"
SECURITY_CFLAGS_remove = "-pie"

AUTOTOOLS_SCRIPT_PATH = "${S}/simpleshell"

do_configure_prepend() {
    sed -i -e 's/-lwesteros_simplebuffer_client/-lwesteros_compositor -lwesteros_simplebuffer_client/g' ${S}/rpi/westeros-sink/Makefile.am
}

do_compile_prepend() {
   export SCANNER_TOOL=${STAGING_BINDIR_NATIVE}/wayland-scanner
   oe_runmake -C ${S}/simpleshell/protocol
}
