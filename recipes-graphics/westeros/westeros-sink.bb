include westeros.inc

SUMMARY = "This receipe compiles the westeros compositor gstreamer sink element"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

DEPENDS += "wayland westeros-simpleshell westeros-simplebuffer"
