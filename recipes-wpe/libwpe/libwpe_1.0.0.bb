require libwpe.inc

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=6ae4db0d4b812334e1539cd5aa6e2f46"

RECIPE_BRANCH ?= "master"
SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEBackend.git;branch=${RECIPE_BRANCH};protocol=https"
SRCREV ?= "4be4c7df5734d125148367a90da477c8d40d9eaf"
S = "${WORKDIR}/git"

CXXFLAGS += "-D_GLIBCXX_USE_CXX11_ABI=0"

