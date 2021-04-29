
require wpewebkit.inc

PV = "2.28+git${SRCPV}"
PR = "r0"

SRCREV ?= "c59af439218a6354b5a79ab0ee2d661f25d55c19"
SRC_URI = "\
    git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=wpe-2.28 \
    file://0001-Fix-for-missing-heap-vm-main.patch \
    file://0001-page-remove-constness-to-Orientation-and-Motion-cont.patch \
"

RCONFLICTS_${PN} = "libwpe (< 1.4) wpebackend-fdo (< 1.4)"
