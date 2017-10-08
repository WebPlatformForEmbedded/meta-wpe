FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
# Add egl/gles to config
PACKAGECONFIG_append = " egl"
PACKAGECONFIG_append_class-target = " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'glesv2', d)}"

SRC_URI += "file://0006-add-egl-device-create.patch"
SRC_URI += "file://0008-add-noaa-compositor.patch"
SRC_URI += "file://0009-error-check-just-in-debug.patch"
