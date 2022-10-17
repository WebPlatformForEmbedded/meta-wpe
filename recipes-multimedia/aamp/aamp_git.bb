SUMMARY = "AAMP Player library"
DESCRIPTION = "Advanced Adaptive Micro Player library support playback of HLS and MPEGDash contents"
HOMEPAGE = "https://github.com/rdkcmf/rdk-aamp"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

# extra DEPENDS
DEPENDS_append = " \
    curl libdash libxml2 cjson aampabr aampmetrics wpeframework \
    gstreamer1.0 gstreamer1.0-plugins-base glib-2.0 util-linux wpeframework-clientlibraries \
"

PV = "0.1.gitr${SRCPV}"

RECIPE_BRANCH ?= "stable2"
SRC_URI = "\
    git://github.com/rdkcmf/rdk-aamp.git;protocol=https;branch=${RECIPE_BRANCH} \
    file://0001-rdk-aamp-disable-getsourceid-chech-temporarily-to-sendsyncevent.patch \
    file://0002-rdk-amp-align-ocdm-drm-adapter-interface.patch \
    file://0003-Fix-finding-JavaScriptCore-includ-path.patch \
"
SRCREV ?= "a72fea4afc3bb8e81fab9f3e6e3604e3ab6f7930"

S = "${WORKDIR}/git"

inherit cmake

AAMP_USE_THUNDER_OCDM_API_0_2 ??= "ON"
PACKAGECONFIG ??= "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 'opencdm', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready', 'playready', '', d)} \
"
PACKAGECONFIG[opencdm] = "\
    -DCMAKE_CDM_DRM=ON \
    -DCMAKE_USE_OPENCDM=ON \
    -DENABLE_SESSION_STATS=ON \
    -DCMAKE_DASH_DRM=ON \
    -DCMAKE_USE_OPENCDM_ADAPTER=ON \
    -DCMAKE_USE_THUNDER_OCDM_API_0_2="${AAMP_USE_THUNDER_OCDM_API_0_2}" \
    ,, \
"
PACKAGECONFIG[playready] = "-DCMAKE_USE_PLAYREADY=ON,-DCMAKE_USE_PLAYREADY=OFF"
PACKAGECONFIG[jsbindings] = "-DCMAKE_WPEWEBKIT_JSBINDINGS=ON,-DCMAKE_WPEWEBKIT_JSBINDINGS=OFF,wpewebkit"

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"
FILES_${PN} += "${libdir}"
FILES_${PN} += "${libdir}"

# Fixme, something is pointing to a non-symlink and that pulls in -dev packages
INSANE_SKIP_${PN} = "dev-deps"

