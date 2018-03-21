SUMMARY = "OpenGL function pointer management library"
HOMEPAGE = "https://github.com/anholt/libepoxy/"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=58ef4c80d401e07bd9ee8b6b58cf464b"

SRC_URI = "https://github.com/anholt/${BPN}/releases/download/${PV}/${BP}.tar.xz \
           file://0001_make_graphic_libs_configureble.patch \
           file://Add-fallback-definition-for-EGL-CAST.patch \
           file://0001-Make-it-possible-to-disable-the-use-of-dlvsym-needed.patch \
           "
SRC_URI[md5sum] = "af4c3ce0fb1143bdc4e43f85695a9bed"
SRC_URI[sha256sum] = "0b808a06c9685a62fca34b680abb8bc7fb2fda074478e329b063c1f872b826f6"
UPSTREAM_CHECK_URI = "https://github.com/anholt/libepoxy/releases"

inherit autotools pkgconfig distro_features_check

REQUIRED_DISTRO_FEATURES = "opengl"

DEPENDS = "util-macros"

PACKAGECONFIG[egl] = "--enable-egl=yes, --enable-egl=no, virtual/egl"
PACKAGECONFIG[x11] = "--enable-glx=yes, --enable-glx=no, virtual/libx11"
PACKAGECONFIG ??= "egl"

GLX_LIB_NAME    ?= "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/libgl',    'broadcom-refsw', 'libv3ddriver.so', 'libGL.so.1', d)}"
EGL_LIB_NAME    ?= "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/egl',      'broadcom-refsw', 'libv3ddriver.so', 'libEGL.so.1', d)}"
GLES1_LIB_NAME  ?= "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/libgles1', 'broadcom-refsw', 'libv3ddriver.so', 'libGLESv1_CM.so.1', d)}"
GLES2_LIB_NAME  ?= "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/libgles2', 'broadcom-refsw', 'libv3ddriver.so', 'libGLESv2.so.1', d)}"

TARGET_CFLAGS += '\
    -DGLX_LIB_NAME="${GLX_LIB_NAME}" \
    -DEGL_LIB_NAME="${EGL_LIB_NAME}" \
    -DGLES1_LIB_NAME="${GLES1_LIB_NAME}" \
    -DGLES2_LIB_NAME="${GLES2_LIB_NAME}" \
'

# there is no meson support in morty, disabling this
#EXTRA_OEMESON_append_libc-musl = " -Dhas-dlvsym=false "
