# Common for WPE Framework plugins
require wpeframework-common.inc

DEPENDS += " wpeframework-clientlibraries"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/*.so"

