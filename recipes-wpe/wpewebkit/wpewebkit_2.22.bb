require wpewebkit.inc

DEFAULT_PREFERENCE = "-1"

PV = "2.22+git${SRCPV}"
PR = "r0"

SRCREV ?= "349ee3d5b58d3a8b15b15e92259c3383cbaa38c4"
SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=wpe-2.22"

DEPENDS += "libgcrypt"
PACKAGECONFIG_append = " webcrypto"

FILES_${PN} += "${libdir}/wpe-webkit-0.1/injected-bundle/libWPEInjectedBundle.so"
FILES_${PN}-web-inspector-plugin += "${libdir}/wpe-webkit-0.1/libWPEWebInspectorResources.so"
