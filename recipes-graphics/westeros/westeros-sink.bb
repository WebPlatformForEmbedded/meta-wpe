include westeros.inc

SUMMARY = "This receipe compiles the westeros compositor gstreamer sink element"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

DEPENDS += "wayland-native wayland westeros-simpleshell westeros-simplebuffer westeros"

do_compile_prepend() {
	oe_runmake -C ${S}/../../protocol
}

CFLAGS += "-I${S}/../../protocol"
