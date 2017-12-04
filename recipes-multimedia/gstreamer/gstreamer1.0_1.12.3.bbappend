
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0001-typefind-min-1k.patch \
    file://0002-Small-robustness-fixes.patch \
    file://0003-protection-add-function-to-filter-system-ids.patch \
"
