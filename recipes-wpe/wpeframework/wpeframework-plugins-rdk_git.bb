SUMMARY = "WPEFramework rdkservices"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=39fb5e7bc6aded7b6d2a5f5aa553425f"
PR = "r1"

require include/wpeframework-plugins.inc

SRC_URI =  "git://git@github.com:/WebPlatformForEmbedded/ThunderNanoServicesRDK.git;protocol=ssh;branch=development/cmake-webkitbrowser"
SRCREV = "1bd797d3abc4166ef3ba61b5a11423ea0152bf5d"

# ----------------------------------------------------------------------------

# More complicated plugins are moved seperate includes

include include/ocdm.inc
include include/webkitbrowser.inc
include include/monitor.inc

# ----------------------------------------------------------------------------

WPEFRAMEWORK_LOCATIONSYNC_URI ?= "http://jsonip.metrological.com/?maf=true"

# ----------------------------------------------------------------------------

PACKAGECONFIG ?= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'clearkey',             'opencdmi_ck', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'netflix',             'monitor_netflix', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm',              'opencdmi', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm',              'monitor_opencdmi', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready',            'opencdmi_pr', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_nexus',      'opencdmi_prnx', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_nexus_svp',  'opencdmi_prnx_svp', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_vg',         'opencdmi_vgrdm', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'widevine',             'opencdmi_wv', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'security',             'securityagent', '', d)} \
    apps deviceinfo locationsync monitor monitor_webkit monitor_webkit_ux monitor_cobalt tracing ux webkitbrowser \
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
"

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/*"
FILES_${PN}-dev += "${libdir}/cmake/*"

INSANE_SKIP_${PN} += "libdir staticdev dev-so"
INSANE_SKIP_${PN}-dbg += "libdir"
