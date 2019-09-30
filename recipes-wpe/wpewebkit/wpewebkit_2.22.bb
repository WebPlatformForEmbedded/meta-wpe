require wpewebkit.inc

DEFAULT_PREFERENCE = "-1"

PV = "2.22+git${SRCPV}"
PR = "r0"

SRCREV ?= "d2788a5e62a348ea253a928808c75b7532ecfa86"
SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=development/wpe-2.22/new-ocdm-interface"

DEPENDS += "libgcrypt"
PACKAGECONFIG_append = " webcrypto"

FILES_${PN} += "${libdir}/wpe-webkit-0.1/injected-bundle/libWPEInjectedBundle.so"
FILES_${PN}-web-inspector-plugin += "${libdir}/wpe-webkit-0.1/libWPEWebInspectorResources.so"
