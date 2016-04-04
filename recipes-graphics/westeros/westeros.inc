LICENSE = "CLOSED"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "${WESTEROS_URI}"
SRCREV = "${WESTEROS_SRCREV}"
WESTEROS_URI ?= "git://git@github.com/Comcast/westeros.git;protocol=ssh"
WESTEROS_SRCREV ?= "be414cb48b825ff10e3f3127347c676c2736ab52"
