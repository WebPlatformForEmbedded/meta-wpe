SRC_URI += "git://github.com/Metrological/icudata;name=icudata"
SRCREV_icudata = "8f8ae3712ea50ea0456ec4c83127f32fa763f5bd"

ICUVER = "${@d.getVar('PV', True).split('.')[0]}"

do_compile_prepend() {
        cp ${WORKDIR}/git/icudt${ICUVER}l.dat ${S}/data/in/
}

