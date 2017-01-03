
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0001-Revert-uridecodebin-Use-the-correct-caps-name-for-MS.patch \
"
