require libwpe.inc

S = "${WORKDIR}/git"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=6ae4db0d4b812334e1539cd5aa6e2f46"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEBackend.git"
SRCREV = "4be4c7df5734d125148367a90da477c8d40d9eaf"

CXXFLAGS += "-D_GLIBCXX_USE_CXX11_ABI=0"

RDEPENDS_${PN}_append_rpi = " wpebackend-rdk "
