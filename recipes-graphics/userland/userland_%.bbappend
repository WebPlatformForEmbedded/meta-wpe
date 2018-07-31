FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://0015-EGL-glplatform.h-define-EGL_CAST.patch"

RPROVIDES_${PN} += " libegl"