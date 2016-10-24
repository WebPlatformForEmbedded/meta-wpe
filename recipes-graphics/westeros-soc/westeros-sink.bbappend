S_rpi = "${WORKDIR}/git/rpi/westeros-sink"
S_hikey-32 = "${WORKDIR}/git/drm/westeros-sink"

LICENSE_LOCATION_rpi = "${S}/../../LICENSE"

CFLAGS_append_rpi = " -DWESTEROS_PLATFORM_RPI -DWESTEROS_INVERTED_Y -DBUILD_WAYLAND -x c++ \
                      -I${STAGING_INCDIR}/interface/vmcs_host/linux"

CFLAGS_append_hikey-32 = " -DWESTEROS_PLATFORM_DRM -x c++"

PACKAGECONFIG[gst1] = "--enable-gstreamer1=yes,--enable-gstreamer1=no,gstreamer1.0"

PACKAGECONFIG_rpi = "gst1"

do_configure_prepend_rpi () {
    sed -i -e 's/-lwesteros_simplebuffer_client/-lwesteros_compositor -lwesteros_simplebuffer_client/g' ${S}/Makefile.am
    ln -sf ../../westeros-sink/westeros-sink.c ${S}
    ln -sf ../../westeros-sink/westeros-sink.h ${S}
}

do_configure_prepend_hikey-32 () {
    ln -sf ../../westeros-sink/westeros-sink.c ${S}
    ln -sf ../../westeros-sink/westeros-sink.h ${S}
}

FILES_${PN} += "${libdir}/gstreamer-*/*.so"
FILES_${PN}-dev += "${libdir}/gstreamer-*/*.la"
FILES_${PN}-dbg += "${libdir}/gstreamer-*/.debug/*"
FILES_${PN}-staticdev += "${libdir}/gstreamer-*/*.a "
