SUMMARY = "WPE Framework plugin for the Amazon's Prime Video native application."
LICENSE = "Apache-2.0"
# Add a LICENSE file to WPEPluginAmazon
LIC_FILES_CHKSUM = "file://README.md;md5=29509c3e80f3a22b82a5ff6a60df5794"

require include/wpeframework-plugins.inc

SRC_URI = "git://git@github.com/Metrological/WPEPluginAmazon.git;protocol=ssh;branch=master"
SRCREV = "f22a3f4326a5ea1825140be064141269f0e1228b"

DEPENDS = "wpeframework libamazon"

inherit cmake pkgconfig

WPE_AMAZON_PRIME_MANUFACTURER ?= ""
WPE_AMAZON_PRIME_MODEL_NAME ?= ""
WPE_AMAZON_PRIME_DTID ?= ""
WPE_AMAZON_PRIME_CHIPSET_NAME ?= ""

EXTRA_OECMAKE = " \
                -DCMAKE_BUILD_TYPE=Debug \
                -DPYTHON_EXECUTABLE=${STAGING_BINDIR_NATIVE}/python3-native/python3 \
                -DPLUGIN_AMAZON_PRIME_MANUFACTURER=${WPE_AMAZON_PRIME_MANUFACTURER} \
                -DPLUGIN_AMAZON_PRIME_MODEL_NAME=${WPE_AMAZON_PRIME_MODEL_NAME} \
                -DPLUGIN_AMAZON_PRIME_DTID=${WPE_AMAZON_PRIME_DTID} \
                -DPLUGIN_AMAZON_PRIME_CHIPSET_NAME=${WPE_AMAZON_PRIME_CHIPSET_NAME} \
                "

FILES_${PN} += " \
                ${sysconfdir}/WPEFramework/plugins/AmazonPrime.json \
                ${libdir}/WPEFramework/plugins/libWPEFrameworkAmazon.so \
                "
