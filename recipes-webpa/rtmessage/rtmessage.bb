SUMMARY = "This recipes is used to compile and install rtmessage component"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

DEPENDS_append = " cjson"
S = "${WORKDIR}/git"
RECIPE_BRANCH ?= "rdk-next"
SRC_URI = "git://code.rdkcentral.com/r/rdk/components/opensource/rtmessage.git;protocol=https;branch=${RECIPE_BRANCH}"

SRCREV ?= "94efc5bdf544b015fad9a8928e535a00ad59549d"

EXTRA_OECMAKE_append = "\
    -DRDKC_BUILD=OFF \
    -DENABLE_RDKLOGGER=OFF \
    -DBUILD_DATAPROVIDER_LIB=OFF \
    -DBUILD_DMCLI=OFF \
    -DBUILD_DMCLI_SAMPLE_APP=OFF \
"

inherit cmake

lcl_maybe_fortify = ""
FILES_${PN}-dev = "${includedir}/"
FILES_${PN} += "${libdir}/"
FILES_${PN} += "${bindir}/"
FILES_${PN}-dev += "${libdir}/cmake"

