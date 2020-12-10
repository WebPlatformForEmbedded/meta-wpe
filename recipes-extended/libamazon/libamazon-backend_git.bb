# TODO: Placeholder license 
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://README.md;md5=3e8e4652020108dca4d09adbf9d3cc43"

SRC_URI = "git://git@github.com/Metrological/amazon-backend.git;protocol=ssh;branch=development/gstreamerclient"
SRCREV = "22b5b548f6ad1dabb66983607db21bafa2177a49"

S = "${WORKDIR}/git"
PR = "r0"
PACKAGES += " ${PN}-SOLIBSDEV"

# wpeframework - Core, GstreamerClient
# wpeframework-clientlibraries - DisplayInfo
DEPENDS = "wpeframework wpeframework-clientlibraries"

inherit pkgconfig cmake

EXTRA_OECMAKE = "-DAMAZON_BUILD_TYPE=testing"

AMAZON_BACKEND_FILES = "${libdir}/libamazon-backend.so \
                        ${libdir}/libamazon_backend_device.so \
                        ${includedir}/* \
                        "

FILES_${PN}     += "${AMAZON_BACKEND_FILES}"
FILES_${PN}-SOLIBSDEV += "${libdir}/.debug/*"
FILES_${PN}-dev = ""
