include westeros.inc

SUMMARY = "This receipe compiles the westeros compositor gstreamer sink element"

S = "${WORKDIR}/git"

SRC_URI += " \
           file://0004-Dispatch-custom-queue-instead-flushing-display.patch \
"

inherit autotools pkgconfig

DEPENDS += "wayland-native wayland westeros-simpleshell westeros-simplebuffer westeros"

do_compile_prepend() {
	oe_runmake -C ${S}/protocol
}

CFLAGS += "-I${S}/protocol"
CFLAGS_append_toolchain-clang = " -Wno-error=c++11-narrowing"
