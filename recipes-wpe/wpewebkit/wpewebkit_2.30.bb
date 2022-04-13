require wpewebkit.inc

PV = "2.30+git${SRCPV}"
PR = "r0"
RECIPE_BRANCH ?= "wpe-2.30"
SRC_URI = "\
    git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=${RECIPE_BRANCH};protocol=https \
    file://216455_builds_with_ENABLE_SERVICE_WORKER_OFF.patch \
    file://0001-Fix-for-missing-heap-vm-main.patch \
    file://0001-page-remove-constness-to-Orientation-and-Motion-cont.patch \
"
SRCREV ?= "d338b22672772a5f33960365472ea1d5633bd1c2"

RCONFLICTS:${PN} = "libwpe (< 1.4) wpebackend-fdo (< 1.6)"

