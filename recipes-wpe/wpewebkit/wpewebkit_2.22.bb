require wpewebkit.inc

PV = "2.22+git${SRCPV}"
PR = "r0"

SRCREV ?= "179ca0f6fb6d5c04b940f81d954b2f5b377fd561"
SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=wpe-2.22"

DEPENDS += "libgcrypt"
PACKAGECONFIG_append = " webcrypto"

FILES_${PN} += "${libdir}/wpe-webkit-0.1/injected-bundle/libWPEInjectedBundle.so"
FILES_${PN}-web-inspector-plugin += "${libdir}/wpe-webkit-0.1/libWPEWebInspectorResources.so"
