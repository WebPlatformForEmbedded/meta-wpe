require recipes-graphics/westeros/westeros.inc

SUMMARY = "This receipe compiles the westeros compositor components for RaspberryPi: westeros-gl, westeros-egl"

S = "${WORKDIR}/git/rpi"

DEPENDS = "wayland virtual/egl glib-2.0"
PROVIDES = "westeros-soc"
RPROVIDES_${PN} = "westeros-soc"

CXXFLAGS_append_rpi = " -I ${STAGING_INCDIR}/interface/vmcs_host/linux/"

SECURITY_CFLAGS_remove = "-fpie"
SECURITY_CFLAGS_remove = "-pie"

DEBIAN_NOAUTONAME_${PN} = "1"
DEBIAN_NOAUTONAME_${PN}-dbg = "1"
DEBIAN_NOAUTONAME_${PN}-dev = "1"
DEBIAN_NOAUTONAME_${PN}-staticdev = "1"

inherit autotools pkgconfig

FILES_${PN} = "${libdir}/*"
