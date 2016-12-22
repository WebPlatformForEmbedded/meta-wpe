
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0001-qtdemux-Use-the-tfdt-decode-time-on-byte-streams-whe.patch \
    file://0005-souphttpsrc-cookie-jar-and-context-query-support.patch \
"
