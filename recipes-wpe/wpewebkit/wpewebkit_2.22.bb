require wpewebkit.inc

DEFAULT_PREFERENCE = "-1"

PV = "2.22+git${SRCPV}"
PR = "r0"

SRCREV ?= "d6901b4d34d3a0c45bfb96115808f28f26c9e686"
SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=master \
           file://0001-make-resume-seek-live-working-for-gst1bcm-18.2.patch"

DEPENDS += "libgcrypt"
PACKAGECONFIG_append = " webcrypto"

FILES_${PN} += "${libdir}/wpe-webkit-0.1/injected-bundle/libWPEInjectedBundle.so"
FILES_${PN}-web-inspector-plugin += "${libdir}/wpe-webkit-0.1/libWPEWebInspectorResources.so"
