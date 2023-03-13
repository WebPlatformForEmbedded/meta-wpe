SUMMARY = "WPE Framework OpenCDMi module for playready"
DESCRIPTION = "OCDM Playready is an abstraction module for Playready DRM interfaces. \
OCDM Playready framework is available for Playready 2.5 and 4 version. "
HOMEPAGE = "https://github.com/rdkcentral/OCDM-Playready"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=66fe57b27abb01505f399ce4405cfea0"

require wpeframework-plugins.inc

DEPENDS_append = " playready"

EXTRA_OECMAKE += "-DPERSISTENT_PATH=${WPEFRAMEWORK_PERSISTENT_PATH}"

FILES_${PN} += "\
    ${datadir}/WPEFramework/OCDM/Playready.drm \
    ${WPEFRAMEWORK_PERSISTENT_PATH} \
"

