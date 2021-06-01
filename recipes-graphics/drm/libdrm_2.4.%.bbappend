FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
    file://0004-meson.build-enable-static-build.patch \
    file://0005-tests-meson.build-disable-nouveau-tests-for-static-b.patch \
"

