require westeros.inc

SUMMARY = "Westeros Compositor - simple-shell component"
DESCRIPTION = "This receipe compiles the westeros compositor simple-shell component"

DEPENDS:append = " glib-2.0"

S = "${WORKDIR}/git"
LICENSE_LOCATION = "${S}/LICENSE"

inherit autotools pkgconfig

SECURITY_CFLAGS:remove = "-fpie"
SECURITY_CFLAGS:remove = "-pie"

AUTOTOOLS_SCRIPT_PATH = "${S}/simpleshell"

do_compile:prepend() {
   export SCANNER_TOOL=${STAGING_BINDIR_NATIVE}/wayland-scanner
   oe_runmake -C ${S}/simpleshell/protocol
}

