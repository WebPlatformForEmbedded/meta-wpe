
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0001-typefind-min-1k.patch \
    file://0002-queue2-Add-the-avg-in-rate-property.patch \
    file://0003-bin-implement-context-propagation-when-adding-elements.patch \
    file://0004-queue2-add-overrun-signal.patch \
"
