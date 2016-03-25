SRC_URI += "git://github.com/Metrological/icudata;name=icudata"
SRCREV_icudata = "414fb7451e956546471757c500ca3ad8d204002b"

ICUVER = "${@d.getVar('PV', True).split('.')[0]}"

do_compile_prepend() {
        cp ${WORKDIR}/git/icudt${ICUVER}l.dat ${S}/data/in/
}

