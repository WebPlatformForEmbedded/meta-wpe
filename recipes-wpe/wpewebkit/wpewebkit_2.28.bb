
require wpewebkit.inc
DEPENDS_append = " libsoup-3.0"

PV = "2.28+git${SRCPV}"
PR = "r0"
RECIPE_BRANCH ?= "wpe-2.28"
SRC_URI = "\
    git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=${RECIPE_BRANCH};protocol=https \
    file://0001-Fix-for-missing-heap-vm-main.patch \
    file://0001-page-remove-constness-to-Orientation-and-Motion-cont.patch \
"
SRCREV ?= "a8cb57195b377b58b8f5ef13d8c60f9b36ed2c7e"

RCONFLICTS_${PN} = "libwpe (< 1.4) wpebackend-fdo (< 1.4)"

