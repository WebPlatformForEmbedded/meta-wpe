SUMMARY = "Host/Native tooling for the Web Platform for Embedded Framework"

require include/wpeframework.inc
inherit cmake pkgconfig native python3native

DEPENDS_append = " python3-native python3-jsonref-native"

OECMAKE_SOURCEPATH = "${WORKDIR}/git/Tools"

FILES_${PN} += "${datadir}/*/Modules/*.cmake"

