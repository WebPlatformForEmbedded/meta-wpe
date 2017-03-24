
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
	file://0001-qtdemux-distinguish-TFDT-with-value-0-from-no-TFDT-a.patch \
    file://0005-souphttpsrc-cookie-jar-and-context-query-support.patch \
"
