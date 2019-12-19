require wpewebkit.inc

PV = "2.22+git${SRCPV}"
PR = "r0"

SRCREV ?= "2c1e49c291a3a67c25b4a508c59a5c3e52c89421"
SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEWebKit.git;branch=wpe-2.22 \
           file://0001-Fix-build-with-musl.patch \
           file://0002-Define-MESA_EGL_NO_X11_HEADERS-when-not-using-GLX.patch \
           file://0001-holepunch-Ensure-that-there-s-a-buffer-available-whe.patch \
        "

DEPENDS += "libgcrypt"
PACKAGECONFIG_append = " webcrypto"

FILES_${PN} += "${libdir}/wpe-webkit-0.1/injected-bundle/libWPEInjectedBundle.so"
FILES_${PN}-web-inspector-plugin += "${libdir}/wpe-webkit-0.1/libWPEWebInspectorResources.so"
