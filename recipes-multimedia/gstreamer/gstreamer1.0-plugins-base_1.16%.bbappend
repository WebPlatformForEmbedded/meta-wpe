FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

PACKAGECONFIG[gl] = "-Dgl=enabled -Dgl_winsys=egl,,"

SRC_URI += "\
    file://0001-meson-add-window-system-egl.patch \
    file://0002-decodebin-Manually-sync-element-states-with-parent-a.patch \
    file://0003-gstglcolorconvert-Reverse-direction-when-asking-for-.patch \
    file://0004-vp9-in-mp4.patches.patch \
"
