SUMMARY = "WPE Framework plugin for the Amazon's Prime Video native application."
LICENSE = "Apache-2.0"
# Add a LICENSE file to WPEPluginAmazon
LIC_FILES_CHKSUM = "file://README.md;md5=29509c3e80f3a22b82a5ff6a60df5794"

require include/wpeframework-plugins.inc

SRC_URI = "git://git@github.com/Metrological/WPEPluginAmazon.git;protocol=ssh;branch=development/ignition_2"
SRCREV = "55461f3616eeb5df5139e2bcba2d96d1b9b013cb"

DEPENDS = "wpeframework libamazon"

inherit cmake pkgconfig

EXTRA_OECMAKE = " \
                -DCMAKE_BUILD_TYPE=Debug \
                -DPYTHON_EXECUTABLE=${STAGING_BINDIR_NATIVE}/python3-native/python3\
                "

FILES_${PN} += " \
                ${sysconfdir}/WPEFramework/plugins/AmazonPrime.json \
                ${libdir}/WPEFramework/plugins/libWPEFrameworkAmazon.so \
                "
