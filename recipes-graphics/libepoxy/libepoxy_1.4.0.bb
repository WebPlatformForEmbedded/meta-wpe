SUMMARY = "OpenGL function pointer management library"
DESCRIPTION = "Epoxy is a library for handling OpenGL function pointer management \
It hides the complexity of dlopen(), dlsym(), glXGetProcAddress(), eglGetProcAddress(), etc. \
from the app developer, with very little knowledge needed on their part. \
They get to read GL specs and write code using undecorated function names like glCompileShader()"
HOMEPAGE = "https://github.com/anholt/libepoxy/"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=58ef4c80d401e07bd9ee8b6b58cf464b"

SRC_URI = "https://github.com/anholt/${BPN}/releases/download/v1.4/${BP}.tar.xz"
SRC_URI[md5sum] = "d8d8cbf2beb64975d424fcc5167a2a38"
SRC_URI[sha256sum] = "25a906b14a921bc2b488cfeaa21a00486fe92630e4a9dd346e4ecabeae52ab41"
UPSTREAM_CHECK_URI = "https://github.com/anholt/libepoxy/releases"

inherit autotools pkgconfig features_check

REQUIRED_DISTRO_FEATURES = "opengl"

DEPENDS_append = " util-macros virtual/egl"

PACKAGECONFIG[x11] = "--enable-glx, --disable-glx, virtual/libx11"
PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)}"
