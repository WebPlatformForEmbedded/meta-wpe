include westeros.inc

SUMMARY = "This receipe compiles the westeros compositor gstreamer sink element"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

DEPENDS += "wayland westeros-simpleshell westeros-simplebuffer"

do_compile_prepend() {
	oe_runmake -C ${WORKDIR}/git/protocol
}

CFLAGS += "-I${WORKDIR}/git/protocol"
