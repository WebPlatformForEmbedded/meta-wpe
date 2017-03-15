
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0005-souphttpsrc-cookie-jar-and-context-query-support.patch \
"
