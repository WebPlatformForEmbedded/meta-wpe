require wpewebkit.inc
DEPENDS_append = " libsoup-3.0"

PV = "2.38+git${SRCPV}"
PR = "r0"
RECIPE_BRANCH ?= "wpe-2.38"
SRC_URI = "\
    git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=${RECIPE_BRANCH};protocol=https \
    file://0001-Fix-for-missing-heap-vm-main.patch \
"
SRCREV ?= "186a14a8194881c5370e0eb3307ddbadd4a75cfb"

RCONFLICTS_${PN} = "libwpe (< 1.4) wpebackend-fdo (< 1.6)"

