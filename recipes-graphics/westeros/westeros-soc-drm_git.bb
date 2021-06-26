require westeros.inc

SUMMARY = "Westeros GL Component for drm supported platforms."
DESCRIPTION = "This recipe compiles the westeros gl component for drm supported platforms, currently Hikey, db410c, db820c and iMX8M"
LICENSE_LOCATION = "${S}/../LICENSE"

DEPENDS_append = " glib-2.0 libdrm virtual/egl"

PROVIDES = "westeros-soc"
S = "${WORKDIR}/git/drm"
COMPATIBLE_MACHINE = "(hikey-32|dragonboard-410c-32|dragonboard-820c-32|poplar|mx8|dragonboard-410c|dragonboard-820c|hikey)"

inherit autotools pkgconfig

CFLAGS_append = " -I${STAGING_INCDIR}/libdrm -DWESTEROS_GL_NO_PLANES"
CFLAGS_remove_mx8 = "-DWESTEROS_GL_NO_PLANES"

CFLAGS_append_mx8 = " -DDRM_NO_NATIVE_FENCE"

SECURITY_CFLAGS_remove = "-fpie"
SECURITY_CFLAGS_remove = "-pie"

DEBIAN_NOAUTONAME_${PN} = "1"
DEBIAN_NOAUTONAME_${PN}-dbg = "1"
DEBIAN_NOAUTONAME_${PN}-dev = "1"
DEBIAN_NOAUTONAME_${PN}-staticdev = "1"

FILES_${PN} += "${libdir}/*"

RPROVIDES_${PN} = "westeros-soc"
