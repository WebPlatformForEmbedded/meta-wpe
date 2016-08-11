S_rpi = "${WORKDIR}/git/rpi/westeros-sink"
LICENSE_LOCATION_rpi = "${S}/../../LICENSE"

CFLAGS_append_rpi = " -DWESTEROS_PLATFORM_RPI -DWESTEROS_INVERTED_Y -DBUILD_WAYLAND -x c++ \
                      -I${STAGING_INCDIR}/interface/vmcs_host/linux"

PACKAGECONFIG[gst1] = "--enable-gstreamer1=yes,--enable-gstreamer1=no,gstreamer1.0"

PACKAGECONFIG_rpi = "gst1"

do_configure_prepend_rpi () {
    ln -sf ../../westeros-sink/westeros-sink.c ${S}
    ln -sf ../../westeros-sink/westeros-sink.h ${S}
}

FILES_${PN} += "${libdir}/gstreamer-*/*.so"
FILES_${PN}-dev += "${libdir}/gstreamer-*/*.la"
FILES_${PN}-dbg += "${libdir}/gstreamer-*/.debug/*"
FILES_${PN}-staticdev += "${libdir}/gstreamer-*/*.a "
