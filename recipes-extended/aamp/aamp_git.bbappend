FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://0001_aamp_ocdm_new.patch \
"
SRCREV = "0910875ac2bd7ce789432bf8ded9a05ac926164c"

PACKAGECONFIG = "opencdm"
PACKAGECONFIG[opencdm]    = "-DCMAKE_DASH_DRM=ON -DCMAKE_USE_OPENCDM_ADAPTER=ON,,"
