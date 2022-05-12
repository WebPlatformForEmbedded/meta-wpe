
require wpewebkit.inc

PV = "2.28+git${SRCPV}"
PR = "r0"
RECIPE_BRANCH ?= "wpe-2.28"
SRC_URI = "\
    git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=${RECIPE_BRANCH};protocol=https \
    file://0001-Fix-for-missing-heap-vm-main.patch \
    file://0001-page-remove-constness-to-Orientation-and-Motion-cont.patch \
"
SRCREV ?= "b2e6a99cdbd58952a56a45c70490704fb5c7cd45"

RCONFLICTS_${PN} = "libwpe (< 1.4) wpebackend-fdo (< 1.4)"

