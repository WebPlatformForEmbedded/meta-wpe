require westeros.inc

SUMMARY = "Westeros Compositor - gstreamer sink element"
DESCRIPTION = "This receipe compiles the westeros compositor gstreamer sink element"

DEPENDS_append = " westeros westeros-simplebuffer westeros-simpleshell"

SRC_URI_append = " file://0004-Dispatch-custom-queue-instead-flushing-display.patch"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_compile_prepend() {
    oe_runmake -C ${S}/protocol
}

CFLAGS += "-I${S}/protocol"
CFLAGS_append_toolchain-clang = " -Wno-error=c++11-narrowing"

