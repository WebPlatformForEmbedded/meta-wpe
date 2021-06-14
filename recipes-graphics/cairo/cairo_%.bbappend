FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
# Add egl/gles to config
PACKAGECONFIG_append_class-target = " egl"
PACKAGECONFIG_append_class-target = " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'glesv2', d)}"

SRC_URI_append = "\
    file://0006-add-egl-device-create.patch \
    file://0009-error-check-just-in-debug.patch \
"

