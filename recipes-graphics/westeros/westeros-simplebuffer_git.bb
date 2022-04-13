require westeros.inc

SUMMARY = "Westeros Compositor - Simplebuffer component"
DESCRIPTION = "This receipe compiles the westeros compositor simplebuffer component"

DEPENDS:append = " glib-2.0 virtual/libgl"

S = "${WORKDIR}/git"
LICENSE_LOCATION = "${S}/LICENSE"

inherit autotools pkgconfig

SECURITY_CFLAGS:remove = "-fpie"
SECURITY_CFLAGS:remove = "-pie"
AUTOTOOLS_SCRIPT_PATH = "${S}/simplebuffer"

do_compile:prepend() {
   export SCANNER_TOOL=${STAGING_BINDIR_NATIVE}/wayland-scanner
   oe_runmake -C ${S}/simplebuffer/protocol
}

do_install:append() {
  install -Dm 0644 ${S}/simplebuffer/protocol/simplebuffer-client-protocol.h ${D}${includedir}/simplebuffer-client-protocol.h
}

