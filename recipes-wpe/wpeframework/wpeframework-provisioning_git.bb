SUMMARY = "WPE Framework Provisioning plugin"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "CLOSED"

DEPENDS = "wpeframework libprovision"

PV = "3.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/WPEPluginProvisioning.git;protocol=ssh;branch=master \
		  file://0002-cmake-Remove-redundant-include.patch"

SRCREV = "105a9444e2ebbee6ae82e61af858242c97a0c4d3"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[debug]            = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"

PROVISIONING_URI = "provisioning-sdk.metrological.com:80"

EXTRA_OECMAKE += " \
    -DWPEFRAMEWORK_PROVISIONING_URI=${PROVISIONING_URI} \
"

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/libWPEFrameworkProvisioning.so"
INSANE_SKIP_${PN} = ""

# ----------------------------------------------------------------------------

TOOLCHAIN = "gcc"
