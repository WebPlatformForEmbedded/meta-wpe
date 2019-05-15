FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Make-graphic-libs-configurable.patch"

GLX_LIB_NAME    ?= "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/libgl',    'broadcom-refsw', 'libv3ddriver.so', '/usr/lib/libGL.so.1', d)}"
EGL_LIB_NAME    ?= "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/egl',      'broadcom-refsw', 'libv3ddriver.so', '/usr/lib/libEGL.so.1', d)}"
GLES1_LIB_NAME  ?= "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/libgles1', 'broadcom-refsw', 'libv3ddriver.so', '/usr/lib/libGLESv1_CM.so.1', d)}"
GLES2_LIB_NAME  ?= "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/libgles2', 'broadcom-refsw', 'libv3ddriver.so', '/usr/lib/libGLESv2.so.2', d)}"

TARGET_CFLAGS += '\
    -DGLX_LIB_NAME="${GLX_LIB_NAME}" \
    -DEGL_LIB_NAME="${EGL_LIB_NAME}" \
    -DGLES1_LIB_NAME="${GLES1_LIB_NAME}" \
    -DGLES2_LIB_NAME="${GLES2_LIB_NAME}" \
'

