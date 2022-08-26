# Configuration file to configure Plugins used from RDKServices
require wpeframework-plugins.inc

# Plugins configs are moved to seperate includes
require deviceidentification.inc
require deviceinfo.inc
require displayinfo.inc
require locationsync.inc
require messagecontrol.inc
require messenger.inc
require monitor.inc
require ocdm.inc
require packager.inc
require playerinfo.inc
require securityagent.inc
require tracecontrol.inc
require warningreportingcontrol.inc
require webkitbrowser.inc

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
    deviceidentification deviceinfo locationsync monitor monitor_webkit tracing webkitbrowser \
"
# FIXME: these config has to be selected based on system config requirement.
# For the time being these are hardcoded
PACKAGECONFIG_append = " \
    monitor_cobalt monitor_webkit_ux \
    webkitbrowser_apps webkitbrowser_ux \
"

EXTRA_OECMAKE += "\
    -DBUILD_REFERENCE=${SRCREV} \
    -DCOMCAST_CONFIG=OFF \
"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/*"
FILES_${PN}-dev += "${libdir}/cmake/*"

INSANE_SKIP_${PN} += "libdir staticdev dev-so"
INSANE_SKIP_${PN}-dbg += "libdir"

