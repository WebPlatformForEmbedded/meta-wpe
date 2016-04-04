# bbappend for raspberryPi
#
PACKAGECONFIG_rpi = "incapp inctest increndergl incsbprotocol xdgv4"
CXXFLAGS_append_rpi = " -DWESTEROS_PLATFORM_RPI -DWESTEROS_INVERTED_Y -DBUILD_WAYLAND -I${STAGING_INCDIR}/interface/vmcs_host/linux"

do_compile_prepend_rpi () {
	export WESTEROS_COMPOSITOR_EXTRA_LIBS="-lEGL -lGLESv2 -lbcm_host"
}
