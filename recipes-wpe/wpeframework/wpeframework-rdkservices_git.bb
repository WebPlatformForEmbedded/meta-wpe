SUMMARY = "WPEFramework rdkservices"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=16cf2209d4e903e4d5dcd75089d7dfe2"
PR = "r1"

require include/wpeframework-plugins.inc

SRC_URI = "git://github.com/rdkcentral/rdkservices.git;protocol=git;branch=master"
SRCREV = "bdb42929f7010db8bda83f3b2d5ec1b2f59e7afd"

# ----------------------------------------------------------------------------

# More complicated plugins are moved seperate includes

include include/ocdm.inc

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
    ${@bb.utils.contains('DISTRO_FEATURES', 'security',             'securityagent', '', d)} \
    deviceinfo locationsync monitor tracing \
"

# ----------------------------------------------------------------------------

WPEFRAMEWORK_DEVICE_IDENTIFICATION_USE_MFR ?= "OFF"
WPEFRAMEWORK_DEVICE_IDENTIFICATION_IMPL ?= ""
PACKAGECONFIG[deviceidentification] = \
                                "-DPLUGIN_DEVICEIDENTIFICATION_USE_MFR=${WPEFRAMEWORK_DEVICE_IDENTIFICATION_USE_MFR} \
                                 -DPLUGIN_DEVICEIDENTIFICATION=ON \
                                 ${WPEFRAMEWORK_DEVICE_IDENTIFICATION_IMPL} \
                                ,-DPLUGIN_DEVICEIDENTIFICATION=OFF,"

PACKAGECONFIG[deviceinfo]     = "-DPLUGIN_DEVICEINFO=ON,-DPLUGIN_DEVICEINFO=OFF,"
PACKAGECONFIG[locationsync]   = "-DPLUGIN_LOCATIONSYNC=ON \
                                 -DPLUGIN_LOCATIONSYNC_URI=${WPEFRAMEWORK_LOCATIONSYNC_URI} \
                                ,-DPLUGIN_LOCATIONSYNC=OFF,"
PACKAGECONFIG[monitor]        = "-DPLUGIN_MONITOR=ON \
                                 -DPLUGIN_WEBKITBROWSER=ON \
                                 -DPLUGIN_WEBKITBROWSER_YOUTUBE=ON \
                                 -DPLUGIN_NETFLIX=ON \
                                 -DPLUGIN_WEBKITBROWSER_MEMORYLIMIT=614400 \
                                 -DPLUGIN_YOUTUBE_MEMORYLIMIT=614400 \
                                 -DPLUGIN_NETFLIX_MEMORYLIMIT=307200 \
                                ,-DPLUGIN_MONITOR=OFF,"
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
