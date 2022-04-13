FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append = " \
    file://0001-typefind-min-1k.patch \
    file://0002-Small-robustness-fixes.patch \
"

