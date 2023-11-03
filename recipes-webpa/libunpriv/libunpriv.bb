SUMMARY = "libcap wrapper "
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

DEPENDS_append = " libcap jsoncpp"
S = "${WORKDIR}/git"
RECIPE_BRANCH ?= "rdk-next"
SRC_URI = "git://code.rdkcentral.com/r/rdk/components/generic/libunpriv.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV = "10cb90eaa07788081d9bf629573a093cd106dc70"

CXXFLAGS_append = "\
    -I${STAGING_INCDIR} \
    -I${STAGING_INCDIR}/jsoncpp \
"

inherit autotools pkgconfig

