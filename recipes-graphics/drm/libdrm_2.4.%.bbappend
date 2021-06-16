FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://0004-meson.build-enable-static-build.patch \
    file://0005-tests-meson.build-disable-nouveau-tests-for-static-b.patch \
"

