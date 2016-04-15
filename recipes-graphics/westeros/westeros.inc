LICENSE = "CLOSED"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "${WESTEROS_URI}"
SRCREV = "${WESTEROS_SRCREV}"
WESTEROS_URI ?= "git://git@github.com/Comcast/westeros.git;protocol=ssh"
WESTEROS_SRCREV ?= "e5556da1d49f7f7026fb8e29b51c3605853817ef"
