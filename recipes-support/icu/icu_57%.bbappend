SRC_URI += "git://github.com/Metrological/icudata;name=icudata"
SRCREV_icudata = "c6e813deb8d75937ebb78b68fa6f828bd45550db"

ICUVER = "${@d.getVar('PV', True).split('.')[0]}"

do_compile_prepend() {
        cp ${WORKDIR}/git/icudt${ICUVER}l.dat ${S}/data/in/
}

