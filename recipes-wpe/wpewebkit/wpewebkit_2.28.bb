
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
SRCREV ?= "fdf2f014629a4b4e93f43cec0be4698aa640b826"

RCONFLICTS_${PN} = "libwpe (< 1.4) wpebackend-fdo (< 1.4)"

