require westeros.inc

SUMMARY = "Westeros GL Component for drm supported platforms."
DESCRIPTION = "This recipe compiles the westeros gl component for drm supported platforms, currently Hikey, db410c, db820c and iMX8M"
LICENSE_LOCATION = "${S}/../LICENSE"

DEPENDS:append = " glib-2.0 libdrm virtual/egl"

PROVIDES = "westeros-soc"
S = "${WORKDIR}/git/drm"
COMPATIBLE_MACHINE = "(hikey-32|dragonboard-410c-32|dragonboard-820c-32|poplar|mx8|dragonboard-410c|dragonboard-820c|hikey)"

inherit autotools pkgconfig

CFLAGS:append = " -I${STAGING_INCDIR}/libdrm -DWESTEROS_GL_NO_PLANES"
CFLAGS:remove_mx8 = "-DWESTEROS_GL_NO_PLANES"

CFLAGS:append_mx8 = " -DDRM_NO_NATIVE_FENCE"

SECURITY_CFLAGS:remove = "-fpie"
SECURITY_CFLAGS:remove = "-pie"

DEBIAN_NOAUTONAME:${PN} = "1"
DEBIAN_NOAUTONAME:${PN}-dbg = "1"
DEBIAN_NOAUTONAME:${PN}-dev = "1"
DEBIAN_NOAUTONAME:${PN}-staticdev = "1"

FILES:${PN} += "${libdir}/*"

RPROVIDES:${PN} = "westeros-soc"
