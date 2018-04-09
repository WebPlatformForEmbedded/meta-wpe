SUMMARY = "WPE Framework Provisioning plugin"
LICENSE = "CLOSED"
DEPENDS += " libprovision"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/WPEPluginProvisioning.git;protocol=ssh;branch=master \
           file://0002-cmake-Remove-redundant-include.patch"
SRCREV = "105a9444e2ebbee6ae82e61af858242c97a0c4d3"

require wpeframework-plugins.inc

PROVISIONING_URI = "provisioning-sdk.metrological.com:80"

EXTRA_OECMAKE += " \
    -DWPEFRAMEWORK_PROVISIONING_URI=${PROVISIONING_URI} \
"
