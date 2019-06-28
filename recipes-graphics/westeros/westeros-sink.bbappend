SINK_SOC_PATH = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', 'v4l2', 'rpi', d)}"
AUTOTOOLS_SCRIPT_PATH = "${S}/drm/westeros-sink"
AUTOTOOLS_SCRIPT_PATH_rpi = "${S}/${SINK_SOC_PATH}/westeros-sink"

LICENSE_LOCATION = "${S}/LICENSE"

CFLAGS_append_rpi = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', \
                     ' -DWESTEROS_PLATFORM_DRM -x c++', \
                     'i -DWESTEROS_PLATFORM_RPI -DWESTEROS_INVERTED_Y -DBUILD_WAYLAND -x c++ -I${STAGING_INCDIR}/interface/vmcs_host/linux', \
                     d)}"

CFLAGS_append = " -DWESTEROS_PLATFORM_DRM -x c++"

PACKAGECONFIG[gst1] = "--enable-gstreamer1=yes,--enable-gstreamer1=no,gstreamer1.0"

PACKAGECONFIG = "gst1"

do_configure_prepend() {
   cp ${S}/westeros-sink/westeros-sink.c ${AUTOTOOLS_SCRIPT_PATH}
   cp ${S}/westeros-sink/westeros-sink.h ${AUTOTOOLS_SCRIPT_PATH}
}

FILES_${PN} += "${libdir}/gstreamer-*/*.so"
FILES_${PN}-dev += "${libdir}/gstreamer-*/*.la"
FILES_${PN}-dbg += "${libdir}/gstreamer-*/.debug/*"
FILES_${PN}-staticdev += "${libdir}/gstreamer-*/*.a "
