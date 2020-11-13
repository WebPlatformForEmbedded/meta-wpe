# TODO: Placeholder license 
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://README.md;md5=3e8e4652020108dca4d09adbf9d3cc43"

SRC_URI = "git://git@github.com/Metrological/amazon-backend.git;protocol=ssh;branch=development/ignition"
SRCREV = "9e4b56e133711a6549645f0543513c02accbb5d1"

S = "${WORKDIR}/git"
PR = "r0"
PACKAGES += " ${PN}-SOLIBSDEV"

# WPEFramework - Core, GstreamerClient
# WPEFramework-RDKServices - DisplayInfo
DEPENDS = "wpeframework wpeframework-rdkservices wpeframework-clientlibraries"

inherit pkgconfig cmake

EXTRA_OECMAKE = "-DAMAZON_BUILD_TYPE=testing"

AMAZON_BACKEND_FILES = "${libdir}/libamazon-backend.so \
                        ${libdir}/libamazon_backend_device.so \
                        ${includedir}/* \
                        "

FILES_${PN}     += "${AMAZON_BACKEND_FILES}"
FILES_${PN}-SOLIBSDEV += "${libdir}/.debug/*"
FILES_${PN}-dev = ""
