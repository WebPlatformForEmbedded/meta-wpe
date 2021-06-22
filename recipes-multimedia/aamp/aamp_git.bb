SUMMARY = "AAMP Player library"
DESCRIPTION = "Advanced Adaptive Micro Player library support playback of HLS and MPEGDash contents"
HOMEPAGE = "https://github.com/rdkcmf/rdk-aamp"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

# extra DEPENDS
DEPENDS_append = " \
    curl libdash libxml2 cjson aampabr wpeframework \
    gstreamer1.0 gstreamer1.0-plugins-base glib-2.0 util-linux wpeframework-clientlibraries \
"

PV = "0.1.gitr${SRCPV}"

SRC_URI = "\
    git://github.com/rdkcmf/rdk-aamp.git;protocol=git;branch=stable2 \
    file://0001-rdk-aamp-disable-getsourceid-chech-temporarily-to-sendsyncevent.patch \
"
SRCREV = "24a4dfa609c7c6b3c18ee595bde0874c9a5ca7c9"

S = "${WORKDIR}/git"

inherit cmake

AAMP_USE_THUNDER_OCDM_API_0_2 ??= "ON"
PACKAGECONFIG ??= " ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 'opencdm', '', d)}"
PACKAGECONFIG[opencdm] = "\
    -DCMAKE_DASH_DRM=ON \
    -DCMAKE_USE_OPENCDM_ADAPTER=ON \
    -DCMAKE_USE_THUNDER_OCDM_API_0_2="${AAMP_USE_THUNDER_OCDM_API_0_2}" \
    ,, \
"

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"
FILES_${PN} += "${libdir}/lib*.so"
FILES_${PN} += "${libdir}/aamp/lib*.so"

# Fixme, something is pointing to a non-symlink and that pulls in -dev packages
INSANE_SKIP_${PN} = "dev-deps"

