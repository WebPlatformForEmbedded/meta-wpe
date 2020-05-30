FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://parodus-lib-to-service-conversion.patch \
"

FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN}-dev += "dev-so"
FILES_${PN}     = "${libdir}/libparodus.so"
