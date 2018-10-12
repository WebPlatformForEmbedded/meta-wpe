SUMMARY = "WPE WebKit port pairs the WebKit engine with OpenGL-ES (OpenGL for Embedded Systems), \
           allowing embedders to create simple and performant systems based on Web platform technologies. \
           It is designed with hardware acceleration in mind, relying on EGL, the Wayland EGL platform, and OpenGL ES."
HOMEPAGE = "https://wpewebkit.org/"
LICENSE = "BSD & LGPLv2+"
LIC_FILES_CHKSUM = "file://Source/WebCore/LICENSE-LGPL-2.1;md5=a778a33ef338abbaf8b8a7c36b6eec80 "
PR = "r2"

require wpewebkit.inc

PV = "2.20+git${SRCPV}"

SRCREV ?= "dde0b5b93fc434ee2ec6375d16815e270a5c9834"
BASE_URI ?= "git://github.com/WebPlatformForEmbedded/WPEWebKit.git"
SRC_URI = "${BASE_URI} \
           file://0001-Define-MESA_EGL_NO_X11_HEADERS-when-not-using-GLX.patch \
           file://0001-Fix-build-with-musl.patch \
           file://0002-include-GraphicsContext3D.h-for-DONT_CARE-definition.patch \
           file://0003-Improve-checking-if-libatomic-is-needed-for-armv6-pr.patch \
           "

# JSC JIT doesn't currently compile on ARMv6, disable it.
EXTRA_OECMAKE_append_armv6 = " -DENABLE_JIT=OFF"
