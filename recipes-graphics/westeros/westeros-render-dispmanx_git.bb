require recipes-graphics/westeros/westeros.inc

SUMMARY = "Westeros compositor - Dispmanx renderer module"
DESCRIPTION = "This receipe compiles the westeros compositor dispmanx render module for Raspberry Pi"

DEPENDS_append = " glib-2.0 westeros virtual/egl"

S = "${WORKDIR}/git"

DEBIAN_NOAUTONAME_${PN} = "1"
DEBIAN_NOAUTONAME_${PN}-dbg = "1"
DEBIAN_NOAUTONAME_${PN}-dev = "1"
DEBIAN_NOAUTONAME_${PN}-staticdev = "1"

SECURITY_CFLAGS_remove = "-fpie"
SECURITY_CFLAGS_remove = "-pie"

COMPATIBLE_MACHINE ?= "null"

inherit autotools pkgconfig

