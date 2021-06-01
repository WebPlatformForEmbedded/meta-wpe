include westeros.inc

SUMMARY = "This receipe compiles the westeros compositor simple-shelk component"

S = "${WORKDIR}/git"
LICENSE_LOCATION = "${S}/LICENSE"

DEPENDS += "wayland wayland-native glib-2.0"

inherit autotools pkgconfig

SECURITY_CFLAGS_remove = "-fpie"
SECURITY_CFLAGS_remove = "-pie"

AUTOTOOLS_SCRIPT_PATH = "${S}/simpleshell"

do_compile_prepend() {
   export SCANNER_TOOL=${STAGING_BINDIR_NATIVE}/wayland-scanner
   oe_runmake -C ${S}/simpleshell/protocol
}

