
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0001-typefind-min-1k.patch \
    file://0002-small-robustness-fixes.patch \
    file://0003-protection-added-function-to-filter-system-ids.patch \
"
