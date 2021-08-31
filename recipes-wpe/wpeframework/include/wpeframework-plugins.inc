# Common for WPE Framework plugins
require wpeframework-common.inc

DEPENDS_append = " wpeframework wpeframework-interfaces wpeframework-clientlibraries"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/*.so"

