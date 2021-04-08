SINK_SOC ??= ""
SINK_SOC_PATH = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', 'v4l2', '${SINK_SOC}', d)}"
AUTOTOOLS_SCRIPT_PATH = "${S}/drm/westeros-sink"

LICENSE_LOCATION = "${S}/LICENSE"

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
