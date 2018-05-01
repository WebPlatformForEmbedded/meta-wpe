SUMMARY = "WPE Framework Provisioning plugin"
LICENSE = "CLOSED"
DEPENDS += " libprovision"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/WPEPluginProvisioning.git;protocol=ssh;branch=master \
           file://0002-cmake-Remove-redundant-include.patch"
SRCREV = "2fb370f62ba3d4cdb6b7728ac3148eac614b9e93"

require wpeframework-plugins.inc

PROVISIONING_URI = "provisioning-sdk.metrological.com:80"

EXTRA_OECMAKE += " \
    -DWPEFRAMEWORK_PROVISIONING_URI=${PROVISIONING_URI} \
"
