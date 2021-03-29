FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

PACKAGECONFIG[gl] = "-Dgl=enabled -Dgl_winsys=egl,,"

SRC_URI += " \
    file://0001-meson-add-window-system-egl.patch \
"
