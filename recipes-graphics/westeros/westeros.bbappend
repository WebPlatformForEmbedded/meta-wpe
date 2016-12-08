# bbappend for raspberryPi
#
PACKAGECONFIG_rpi = "incapp inctest increndergl incsbprotocol xdgv4"
CXXFLAGS_append_rpi = " -DWESTEROS_PLATFORM_RPI -DWESTEROS_INVERTED_Y -DBUILD_WAYLAND -I${STAGING_INCDIR}/interface/vmcs_host/linux"

do_configure_prepend_rpi() {
    sed -i -e 's/-lwesteros_simplebuffer_client/-lwesteros_compositor -lwesteros_simplebuffer_client/g' ${S}/rpi/westeros-sink/Makefile.am
}

do_compile_prepend_rpi () {
	export WESTEROS_COMPOSITOR_EXTRA_LIBS="-lEGL -lGLESv2 -lbcm_host"
}

# bbappend for DRM support
#
PACKAGECONFIG_hikey-32 = "incapp inctest increndergl incsbprotocol xdgv5"
CXXFLAGS_append_hikey-32 = " -DWESTEROS_PLATFORM_DRM"

do_configure_prepend_hikey-32() {
    sed -i -e 's/-lwesteros_simplebuffer_client/-lwesteros_compositor -lwesteros_simplebuffer_client/g' ${S}/drm/westeros-sink/Makefile.am
}

PACKAGECONFIG_dragonboard-410c-32 = "incapp inctest increndergl incsbprotocol xdgv5"
CXXFLAGS_append_dragonboard-410c-32 = " -DWESTEROS_PLATFORM_DRM"

do_configure_prepend_dragonboard-410c-32() {
    sed -i -e 's/-lwesteros_simplebuffer_client/-lwesteros_compositor -lwesteros_simplebuffer_client/g' ${S}/drm/westeros-sink/Makefile.am
}
