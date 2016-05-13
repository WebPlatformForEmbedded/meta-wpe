
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0002-qtdemux-PIFF-box-detection-and-minimal-parsing-suppo.patch \
    file://0004-Use-the-tfdt-decode-time-when-it-s-significantly-dif.patch \
    file://0005-souphttpsrc-cookie-jar-and-context-query-support.patch \
    file://0007-Revert-qtdemux-expose-streams-with-first-moof-for-fr.patch \
"
