require wpewebkit.inc

PV = "2.22+git${SRCPV}"
PR = "r0"

SRCREV ?= "007e0622929fc53e7979ce5088bf0c70cdbb16d8"
SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=wpe-2.22 \
           file://0001-WPE-Fix-packageconfig-include-path.patch \
           "

#           file://0001-Define-MESA_EGL_NO_X11_HEADERS-when-not-using-GLX.patch 
#           file://0001-Fix-build-with-musl.patch 
#           file://0002-include-GraphicsContext3D.h-for-DONT_CARE-definition.patch 
#           file://0003-Improve-checking-if-libatomic-is-needed-for-armv6-pr.patch 
#           "

# FIXME: 2.22 won't compile without libgcrypt in the dependencies
DEPENDS += "libgcrypt"
PACKAGECONFIG_append = " webcrypto"

# FIXME: EME has an compile issue, disable for now
PACKAGECONFIG_remove = "encryptedmedia"

FILES_${PN} += "${libdir}/wpe-webkit-0.1/injected-bundle/libWPEInjectedBundle.so"
FILES_${PN}-web-inspector-plugin += "${libdir}/wpe-webkit-0.1/libWPEWebInspectorResources.so"
