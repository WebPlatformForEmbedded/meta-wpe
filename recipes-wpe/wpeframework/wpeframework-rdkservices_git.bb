SUMMARY = "WPEFramework rdkservices"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=16cf2209d4e903e4d5dcd75089d7dfe2"
PR = "r1"

require include/wpeframework-plugins.inc

SRC_URI = "git://github.com/rdkcentral/rdkservices.git;protocol=git;branch=sprint/2101 \
"

SRCREV = "776c2e4d7b35c57f0c0f0e302ea3d87fb2f3270f"

# ----------------------------------------------------------------------------

# More complicated plugins are moved seperate includes

include include/ocdm.inc
include include/monitor.inc
include include/webkitbrowser.inc

# ----------------------------------------------------------------------------

WPEFRAMEWORK_LOCATIONSYNC_URI ?= "http://jsonip.metrological.com/?maf=true"

# ----------------------------------------------------------------------------

PACKAGECONFIG ?= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm',              'opencdmi', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'clearkey',             'opencdmi_ck', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready',            'opencdmi_pr', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_nexus',      'opencdmi_prnx', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_nexus_svp',  'opencdmi_prnx_svp', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_vg',         'opencdmi_vgrdm', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'widevine',             'opencdmi_wv', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'widevine_nexus_svp',   'opencdmi_wvnx_svp', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'security',             'securityagent', '', d)} \
    monitor monitor_webkit monitor_webkit_ux monitor_opencdmi \
    deviceinfo locationsync tracing webkitbrowser \
"

# ----------------------------------------------------------------------------

WPEFRAMEWORK_DEVICE_IDENTIFICATION_USE_MFR ?= "OFF"
WPEFRAMEWORK_DEVICE_IDENTIFICATION_IMPL ?= ""
PACKAGECONFIG[deviceidentification] = \
                                "-DPLUGIN_DEVICEIDENTIFICATION=ON \
                                 -DPLUGIN_DEVICEIDENTIFICATION_USE_MFR=${WPEFRAMEWORK_DEVICE_IDENTIFICATION_USE_MFR} \
                                 ${WPEFRAMEWORK_DEVICE_IDENTIFICATION_IMPL} \
                                ,-DPLUGIN_DEVICEIDENTIFICATION=OFF,"

PACKAGECONFIG[deviceinfo]     = "-DPLUGIN_DEVICEINFO=ON,-DPLUGIN_DEVICEINFO=OFF,"
PACKAGECONFIG[locationsync]   = "-DPLUGIN_LOCATIONSYNC=ON \
                                 -DPLUGIN_LOCATIONSYNC_URI=${WPEFRAMEWORK_LOCATIONSYNC_URI} \
                                ,-DPLUGIN_LOCATIONSYNC=OFF,"
PACKAGECONFIG[messenger]      = "-DPLUGIN_MESSENGER=ON,-DPLUGIN_MESSENGER=OFF,"
PACKAGECONFIG[tracing]        = "-DPLUGIN_TRACECONTROL=ON,-DPLUGIN_TRACECONTROL=OFF,"
PACKAGECONFIG[securityagent]  = "-DPLUGIN_SECURITYAGENT=ON,-DPLUGIN_SECURITYAGENT=OFF,"
PACKAGECONFIG[packager]       = "-DPLUGIN_PACKAGER=ON, -DPLUGIN_PACKAGER=OFF,,opkg"

# ----------------------------------------------------------------------------

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DCOMCAST_CONFIG=OFF \
"

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/*"
FILES_${PN}-dev += "${libdir}/cmake/*"

INSANE_SKIP_${PN} += "libdir staticdev dev-so"
INSANE_SKIP_${PN}-dbg += "libdir"
