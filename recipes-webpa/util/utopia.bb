SUMMARY = "CCSP Utopia"
HOMEPAGE = "http://github.com/belvedere-yocto/Utopia"

LICENSE = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=baa21dec03307f641a150889224a157f"


DEPENDS_append = " ccsp-common-library telemetry"

RDEPENDS_${PN}_append_dunfell = " bash"

require recipes-webpa/ccsp/ccsp_common.inc
RECIPE_BRANCH ?= "rdk-next"
SRC_URI = "\
    git://code.rdkcentral.com/r/rdkb/components/opensource/ccsp/Utopia.git;protocol=https;branch=${RECIPE_BRANCH} \
    file://0001-support-non-rdk-build-with-syscfg-alone.patch \
    file://syscfg.db \
"

SRCREV = "2173ef621df472162671f5ed8d0878df28fc112c"
PV = "git${SRCPV}"
S = "${WORKDIR}/git"


inherit autotools pkgconfig

SECURITY_STRINGFORMAT = ""

CFLAGS_append = " \
    -DSAFEC_DUMMY_API \
    -I${STAGING_INCDIR}/ccsp \
    -I${STAGING_INCDIR}/tirpc \
"

LDFLAGS_append = " -ltirpc -lrt -lpthread"
EXTRA_OECONF_append = " --enable-rdk_feature_support=no"

do_install_append () {
    install -D -m 0644 ${S}/source/syscfg/lib/syscfg.h ${D}${includedir}/syscfg/syscfg.h

    # Creating symbolic links to install files in specific directory as in legacy builds
    ln -sf /usr/bin/syscfg ${D}${bindir}/syscfg_create
    ln -sf /usr/bin/syscfg ${D}${bindir}/syscfg_destroy
    ln -sf /usr/bin/syscfg ${D}${bindir}/syscfg_format
    ln -sf /usr/bin/syscfg ${D}${bindir}/syscfg_check

    install -d ${D}/usr/ccsp
    install -m 644 ${S}/../syscfg.db ${D}/usr/ccsp
}

FILES_${PN} += " /usr/ccsp ${libdir} ${bindir}"

