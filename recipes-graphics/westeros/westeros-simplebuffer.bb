include westeros.inc

SUMMARY = "This receipe compiles the westeros compositor simplebuffer component"

S = "${WORKDIR}/git/simplebuffer"
LICENSE_LOCATION = "${S}/../LICENSE"

DEPENDS = "virtual/libgl wayland-native wayland glib-2.0"

inherit autotools pkgconfig

SECURITY_CFLAGS_remove = "-fpie"
SECURITY_CFLAGS_remove = "-pie"

do_configure_prepend() {
    sed -i -e 's/-lwesteros_simplebuffer_client/-lwesteros_compositor -lwesteros_simplebuffer_client/g' ${S}/../rpi/westeros-sink/Makefile.am
}

do_compile_prepend() {
   export SCANNER_TOOL=${STAGING_BINDIR_NATIVE}/wayland-scanner
   oe_runmake -C ${S}/protocol
}

do_install_append() {
  install -Dm 0644 ${S}/protocol/simplebuffer-client-protocol.h ${D}${includedir}/simplebuffer-client-protocol.h
}

