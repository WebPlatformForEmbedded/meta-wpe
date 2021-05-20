SUMMARY = "WPEFramework Plugins RDK"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=39fb5e7bc6aded7b6d2a5f5aa553425f"
PR = "r1"

SRC_URI = "git://git@github.com:/WebPlatformForEmbedded/ThunderNanoServicesRDK.git;protocol=ssh;branch=master"
SRCREV = "aefc318b470b41ad8ea0858c01292970f1e8e8af"

# ----------------------------------------------------------------------------

# Plugins configs are moved to seperate includes
require include/deviceidentification.inc
require include/deviceinfo.inc
require include/locationsync.inc
require include/messenger.inc
include include/monitor.inc
include include/ocdm.inc
require include/packager.inc
require include/securityagent.inc
require include/tracecontrol.inc
include include/webkitbrowser.inc
require include/wpeframework-plugins.inc

# ----------------------------------------------------------------------------

PACKAGECONFIG ??= "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'clearkey', 'opencdmi_ck', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'netflix', 'monitor_netflix', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 'opencdmi', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 'monitor_opencdmi', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready', 'opencdmi_pr', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_nexus', 'opencdmi_prnx', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_nexus_svp', 'opencdmi_prnx_svp', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_vg', 'opencdmi_vgrdm', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'widevine', 'opencdmi_wv', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'security', 'securityagent', '', d)} \
    deviceinfo locationsync monitor monitor_webkit monitor_webkit_ux monitor_cobalt tracing webkitbrowser webkitbrowser_apps webkitbrowser_ux \
"

# ----------------------------------------------------------------------------

EXTRA_OECMAKE += "\
    -DBUILD_REFERENCE=${SRCREV} \
"

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/*"
FILES_${PN}-dev += "${libdir}/cmake/*"

INSANE_SKIP_${PN} += "libdir staticdev dev-so"
INSANE_SKIP_${PN}-dbg += "libdir"
