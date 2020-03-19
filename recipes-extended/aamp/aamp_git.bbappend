FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://0001_aamp_ocdm_adapter.patch \
           file://0002_aamp_gl_image_sink_set_rectangle_support.patch \
           file://0003_aamp_mpd_iso8601_duration_parse.patch \
"
SRCREV = "0910875ac2bd7ce789432bf8ded9a05ac926164c"

PACKAGECONFIG = " ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 'opencdm', '', d)}"
PACKAGECONFIG[opencdm]    = "-DCMAKE_DASH_DRM=ON -DCMAKE_USE_OPENCDM_ADAPTER=ON,,"
