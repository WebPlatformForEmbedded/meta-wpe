SUMMARY = "APL Client Library"
DESCRIPTION = "Recipe to build APL Client Library"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

FILESEXTRAPATHS_prepend:= "${THISDIR}/files:"

OECMAKE_GENERATOR = "Unix Makefiles"

RECIPE_BRANCH ?= "main"
SRC_URI = "git://github.com/alexa/apl-client-library.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "v${PV}"
S = "${WORKDIR}/git"

inherit pkgconfig cmake

DEPENDS_append = " alsa-lib apl-core-library asio websocketpp"

EXTRA_OECMAKE += "\
    -DSANDBOX=ON \
    -DAPL_CORE=ON \
    -DAPLCORE_INCLUDE_DIR="${STAGING_DIR_HOST}${includedir}/apl" \
    -DAPLCORE_BUILD_INCLUDE_DIR="${STAGING_DIR_HOST}${includedir}" \
    -DAPLCORE_RAPIDJSON_INCLUDE_DIR="${STAGING_DIR_HOST}${includedir}" \
    -DAPLCORE_LIB_DIR="${STAGING_DIR_HOST}${libdir}" \
    -DYOGA_INCLUDE_DIR="${STAGING_DIR_HOST}${includedir}/yoga" \
    -DYOGA_LIB_DIR="${STAGING_DIR_HOST}${libdir}" \
    -DWEBSOCKETPP_INCLUDE_DIR="${STAGING_DIR_HOST}${includedir}/websocketpp" \
    -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
    -DENABLE_PIC=ON \
    -DUSE_SYSTEM_RAPIDJSON=ON \
"

FILES_${PN} = "${libdir} ${datadir}"
INSANE_SKIP_${PN}-dev += "ldflags dev-elf"

