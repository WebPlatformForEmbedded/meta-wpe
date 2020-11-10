# Add compressed data set for Netflix / WebKit
SRC_URI += "git://github.com/Metrological/icudata;name=icudata;branch=master"
SRCREV_icudata = "d8d56c495af970036f4afa3704f58609446a2853"

ICUVER = "${@d.getVar('PV', True).split('.')[0]}"

do_compile_prepend() {
        cp ${WORKDIR}/git/icudt${ICUVER}l.dat ${S}/data/in/
}
