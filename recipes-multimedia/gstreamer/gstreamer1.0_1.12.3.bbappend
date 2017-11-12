
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0001-typefind-min-1k.patch \
    file://gstreamer-0001-protection-added-function-to-filter-system-ids.patch \
"
