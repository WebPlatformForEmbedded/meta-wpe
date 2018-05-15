SUMMARY = "WPE Framework Provisioning plugin"
LICENSE = "CLOSED"
DEPENDS += " libprovision"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/WPEPluginProvisioning.git;protocol=ssh;branch=master \
           file://0002-cmake-Remove-redundant-include.patch"
SRCREV = "d7aa48c4e71b0888f1a5f0b59349433046c75da9"

require wpeframework-plugins.inc

PROVISIONING_URI = "provisioning-sdk.metrological.com:80"
OPERATOR = "Metrological"

EXTRA_OECMAKE += " \
    -DWPEFRAMEWORK_PROVISIONING_URI=${PROVISIONING_URI} \
    -DPLUGIN_PROVISIONING_OPERATOR=${OPERATOR} \
"
