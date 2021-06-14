FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI_append = "\
    file://0001-typefind-min-1k.patch \
    file://0002-Small-robustness-fixes.patch \
"

