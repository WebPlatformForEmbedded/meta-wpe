require recipes-graphics/westeros/westeros.inc

SUMMARY = "Westeros compositor - Dispmanx renderer module"
DESCRIPTION = "This receipe compiles the westeros compositor dispmanx render module for Raspberry Pi"

DEPENDS:append = " glib-2.0 westeros virtual/egl"

S = "${WORKDIR}/git"

DEBIAN_NOAUTONAME:${PN} = "1"
DEBIAN_NOAUTONAME:${PN}-dbg = "1"
DEBIAN_NOAUTONAME:${PN}-dev = "1"
DEBIAN_NOAUTONAME:${PN}-staticdev = "1"

SECURITY_CFLAGS:remove = "-fpie"
SECURITY_CFLAGS:remove = "-pie"

COMPATIBLE_MACHINE ?= "null"

inherit autotools pkgconfig

