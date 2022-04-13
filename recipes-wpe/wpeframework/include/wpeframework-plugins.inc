# Common for WPE Framework plugins
require wpeframework-common.inc

DEPENDS:append = " wpeframework wpeframework-interfaces wpeframework-clientlibraries"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/wpeframework/plugins/*.so"

