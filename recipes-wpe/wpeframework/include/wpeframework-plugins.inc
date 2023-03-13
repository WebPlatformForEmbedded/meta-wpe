# Common for WPE Framework plugins
require wpeframework.inc

DEPENDS_append = " wpeframework-tools-native wpeframework wpeframework-interfaces wpeframework-clientlibraries"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/*.so"

