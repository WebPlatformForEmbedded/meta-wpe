require wpewebkit.inc
DEPENDS_append = " libsoup-3.0"

PV = "2.38+git${SRCPV}"
PR = "r0"
RECIPE_BRANCH ?= "wpe-2.38"
SRC_URI = "\
    git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=${RECIPE_BRANCH};protocol=https \
"

SRCREV ?= "5ded44c824881b2193a2d7561a851e73d660586a"

PACKAGECONFIG_append_dunfell = " streamer_native_video"
RCONFLICTS_${PN} = "libwpe (< 1.4) wpebackend-fdo (< 1.6)"

