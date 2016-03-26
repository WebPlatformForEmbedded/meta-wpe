LICENSE = "CLOSED"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "${WESTEROS_URI}"
SRCREV = "${WESTEROS_SRCREV}"
WESTEROS_URI ?= "git://git@github.com/Comcast/westeros.git;protocol=ssh"
WESTEROS_SRCREV ?= "f42b58c4a6713eb019127200ad96862e794bd75c"
