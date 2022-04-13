FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
# Add egl/gles to config
PACKAGECONFIG:append:class-target = " egl"
PACKAGECONFIG:append:class-target = " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'glesv2', d)}"

SRC_URI:append = " \
    file://0006-add-egl-device-create.patch \
    file://0009-error-check-just-in-debug.patch \
"

