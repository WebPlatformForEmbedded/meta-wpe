SUMMARY = "This recipe compiles Telemetry"
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

DEPENDS_append = " curl cjson glib-2.0 rbus"
S = "${WORKDIR}/git"
RECIPE_BRANCH ?= "rdk-next"
SRC_URI = "\
    git://code.rdkcentral.com/r/rdk/components/generic/telemetry.git;protocol=https;branch=${RECIPE_BRANCH} \
    file://0001-compile-telemetry-for-non-rdk-build.patch \
"
SRCREV ?= "36e2d55d36242dd716eeb607667c90247cc8fa51"

PV = "git${SRCPV}"

RDEPENDS_${PN} += "curl cjson glib-2.0 rbus"

inherit pkgconfig autotools systemd pythonnative

LDFLAGS_append = "\
    -lpthread \
    -lstdc++ \
"

do_install_append () {
    install -d ${D}/usr/include/
    install -d ${D}/lib/rdk/
    install -d ${D}${systemd_unitdir}/system
    install -m 644 ${S}/include/telemetry_busmessage_sender.h ${D}/usr/include/
    install -m 644 ${S}/include/telemetry2_0.h ${D}/usr/include/
    install -m 0755 ${S}/source/commonlib/t2Shared_api.sh ${D}${base_libdir}/rdk
    rm -fr ${D}/usr/lib/libtelemetry_msgsender.la 
}

FILES_${PN} = "\
    ${bindir}/telemetry2_0 \
    ${bindir}/t2rbusMethodSimulator \
    ${bindir}/telemetry2_0_client \
    ${systemd_unitdir}/system \
"
FILES_${PN} += "${libdir}/*.so*"
FILES_${PN} += "${base_libdir}/rdk/*"

FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} += "dev-so"

