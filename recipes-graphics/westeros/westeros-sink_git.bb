require westeros.inc

SUMMARY = "Westeros Compositor - gstreamer sink element"
DESCRIPTION = "This receipe compiles the westeros compositor gstreamer sink element"

DEPENDS:append = " westeros westeros-simplebuffer westeros-simpleshell"

SRC_URI:append = " file://0004-Dispatch-custom-queue-instead-flushing-display.patch"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_compile:prepend() {
    oe_runmake -C ${S}/protocol
}

CFLAGS += "-I${S}/protocol"
CFLAGS:append:toolchain-clang = " -Wno-error=c++11-narrowing"

