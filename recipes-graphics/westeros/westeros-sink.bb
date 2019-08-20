include westeros.inc

SUMMARY = "This receipe compiles the westeros compositor gstreamer sink element"

SRC_URI += "file://0001-v4l2-westeros-sink-Include-fcntl.h-for-O_RDWR.patch"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

DEPENDS += "wayland-native wayland westeros-simpleshell westeros-simplebuffer westeros"

do_compile_prepend() {
	oe_runmake -C ${S}/protocol
}

CFLAGS += "-I${S}/protocol"
CFLAGS_append_toolchain-clang = " -Wno-error=c++11-narrowing"
