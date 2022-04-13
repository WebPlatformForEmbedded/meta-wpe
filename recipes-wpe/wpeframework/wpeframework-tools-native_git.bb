SUMMARY = "Host/Native tooling for the WPEFramework/Thunder"
DESCRIPTION = "Host/Native tooling (i.e generators) required to build Thunder Framework"

require include/wpeframework.inc

DEPENDS:append = " python3-native python3-jsonref-native"

PV = "3.0+git${SRCPV}"
OECMAKE_SOURCEPATH = "${WORKDIR}/git/Tools"

inherit cmake pkgconfig native python3native

FILES:${PN} += "${datadir}/*/Modules/*.cmake"

