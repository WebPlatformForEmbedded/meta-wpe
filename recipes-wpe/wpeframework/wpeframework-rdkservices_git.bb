SUMMARY = "WPEFramework rdkservices"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=16cf2209d4e903e4d5dcd75089d7dfe2"
PR = "r1"

require include/wpeframework-plugins.inc

SRC_URI = "git://github.com/rdkcentral/rdkservices.git;protocol=git;branch=master"
SRCREV = "8c1eb6665268da539d465753285d078cfed1ba4e"

SRC_URI += "file://0001-remove-default-services.patch"

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

PACKAGECONFIG[deviceinfo]     = "-DENABLE_DEVICE_INFO=ON,-DENABLE_DEVICE_INFO=OFF,"
PACKAGECONFIG[locationsync]   = "-DENABLE_LOCATION_SYNC=ON \
                                 -DPLUGIN_LOCATIONSYNC_URI=${WPEFRAMEWORK_LOCATIONSYNC_URI} \
                                ,-DENABLE_LOCATION_SYNC=OFF,"
PACKAGECONFIG[monitor]        = "-DENABLE_MONITOR=ON \
                                 -DPLUGIN_WEBKITBROWSER=ON \
                                 -DPLUGIN_WEBKITBROWSER_YOUTUBE=ON \
                                 -DPLUGIN_NETFLIX=ON \
                                 -DPLUGIN_WEBKITBROWSER_MEMORYLIMIT=614400 \
                                 -DPLUGIN_YOUTUBE_MEMORYLIMIT=614400 \
                                 -DPLUGIN_NETFLIX_MEMORYLIMIT=307200 \
                                ,-DENABLE_MONITOR=OFF,"
PACKAGECONFIG[tracing]        = "-DENABLE_TRACE_CONTROL=ON,-DENABLE_TRACE_CONTROL=OFF,"
PACKAGECONFIG[securityagent]  = "-DENABLE_SECURITY_AGENT=ON,-DENABLE_SECURITY_AGENT=OFF,"
PACKAGECONFIG[packager]       = "-DPLUGIN_PACKAGER=ON, -DPLUGIN_PACKAGER=OFF,,opkg"

# ----------------------------------------------------------------------------

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DPLUGIN_DISPLAYSETTINGS=OFF \
    -DPLUGIN_LOGGING_PREFERENCES=OFF \
    -DPLUGIN_USER_PREFERENCES=OFF \
    -DPLUGIN_SYSTEMSERVICES=OFF \
    -DPLUGIN_CONTROLSERVICE=OFF \
"

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/*"
FILES_${PN}-dev += "${libdir}/cmake/*"

INSANE_SKIP_${PN} += "libdir staticdev dev-so"
INSANE_SKIP_${PN}-dbg += "libdir"
