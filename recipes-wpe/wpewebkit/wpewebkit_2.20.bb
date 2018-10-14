require wpewebkit.inc

PV = "2.20+git${SRCPV}"
PR = "r0"

SRCREV ?= "dde0b5b93fc434ee2ec6375d16815e270a5c9834"
SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEWebKit.git"
SRC_URI += "file://0001-Define-MESA_EGL_NO_X11_HEADERS-when-not-using-GLX.patch"
SRC_URI += "file://0001-Fix-build-with-musl.patch"
SRC_URI += "file://0002-include-GraphicsContext3D.h-for-DONT_CARE-definition.patch"
SRC_URI += "file://0003-Improve-checking-if-libatomic-is-needed-for-armv6-pr.patch"

# JSC JIT doesn't currently compile on ARMv6, disable it.
EXTRA_OECMAKE_append_armv6 += " -DENABLE_JIT=OFF"
