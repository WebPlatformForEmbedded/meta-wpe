SUMMARY = "APL Core Library"
DESCRIPTION = "Recipe to build APL Core"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d92e60ee98664c54f68aa515a6169708"

FILESEXTRAPATHS_prepend:= "${THISDIR}/files:"

OECMAKE_GENERATOR = "Unix Makefiles"

RECIPE_BRANCH ?= "master"
SRC_URI = "git://github.com/alexa/apl-core-library.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "v${PV}"
S = "${WORKDIR}/git"


LDFLAGS += "-pthread"

inherit pkgconfig cmake

DEPENDS_append = " rapidjson"

EXTRA_OECMAKE += "\
     -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
     -DENABLE_PIC=ON \
     -DUSE_SYSTEM_RAPIDJSON=ON \
"

FILES_${PN} = "${libdir}"
FILES_${PN}-dev = "${includedir}"

