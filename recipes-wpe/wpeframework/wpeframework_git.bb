SUMMARY = "Web Platform for Embedded Framework"
DESCRIPTION = "A C++ platform abstraction layer for generic functionality."

require include/wpeframework.inc
require include/wpeframework-common.inc
require include/wpeframework-deprecated.inc
require ${@oe.utils.conditional('WPEFRAMEWORK_GROUP', '', '', 'include/usergroup/wpeframework.inc', d)}

DEPENDS_append = " zlib virtual/egl"
DEPENDS_append_libc-musl = " libexecinfo"

PROVIDES += "thunder"

SRC_URI_append = "\
   file://20-video-device-udev.rules.in \
   file://0001-cmake-tool-path-settings.patch \
"
PV = "3.0+gitr${SRCPV}"
inherit python3native systemd update-rc.d

WPEFRAMEWORK_SYSTEM_PREFIX ??= "WPE"
WPEFRAMEWORK_PORT ??= "80"
WPEFRAMEWORK_IDLE_TIME ??= "180"
WPEFRAMEWORK_ETHERNETCARD_NAME ??= "eth0"
WPEFRAMEWORK_SOFT_KILL_CHECK_WAIT_TIME ??= "10"
WPEFRAMEWORK_HARD_KILL_CHECK_WAIT_TIME ??= "4"

WPEFRAMEWORK_TRACING_LEVEL ??= "0"
WPEFRAMEWORK_INITSCRIPT_SYSTEMD_EXTRA_DEPENDS ??= ""
WPEFRAMEWORK_INITSCRIPT_SYSTEM_ROOT_PATH ??= "home/root"
WPEFRAMEWORK_INITSCRIPT_SYSTEMD_SERVICE ??= "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}"

PACKAGECONFIG ??= "\
    ${@bb.utils.contains('MACHINE_FEATURES', 'bluetooth', 'bluetooth_support', '', d)} \
    hidenonexternalsymbols initscriptsupport messaging \
    ${@bb.utils.contains('DISTRO_FEATURES', 'provisioning', 'securesocket', '', d)} \
    webserver_autoresume webkitbrowser_autoresume \
"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_debug', 'debug', '', d)}"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_debugoptimized', 'debugoptimized', '', d)}"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_production', 'production', '', d)}"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_release', 'release', '', d)}"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_releasesymbols', 'releasesymbols', '', d)}"

# CMAKE Build Type
# DISTRO_FEATURES should be set to propagate build type across all necesary components
# like, DISTRO_FEATURES_append = "thunder_release"
PACKAGECONFIG[debug] = "-DCMAKE_BUILD_TYPE=Debug,,"
PACKAGECONFIG[debugoptimized] = "-DCMAKE_BUILD_TYPE=DebugOptimized,,"
PACKAGECONFIG[releasesymbols] = "-DCMAKE_BUILD_TYPE=RelWithDebInfo,,"
PACKAGECONFIG[release] = "-DCMAKE_BUILD_TYPE=Release,,"
PACKAGECONFIG[production] = "-DCMAKE_BUILD_TYPE=MinSizeRel,,"

PACKAGECONFIG[bluetooth_support] = "-DBLUETOOTH_SUPPORT=ON,-DBLUETOOTH_SUPPORT=OFF,"
PACKAGECONFIG[processcontainers] = "-DPROCESSCONTAINERS=ON,-DPROCESSCONTAINERS=OFF,libcgroup collectd cgroup-lite, cgroup-lite"
PACKAGECONFIG[processcontainers_awc] = "-DPROCESSCONTAINERS_AWC=ON,-DPROCESSCONTAINERS_AWC=OFF,"
PACKAGECONFIG[processcontainers_clib] = "-DPROCESSCONTAINERS_CLIB=ON,-DPROCESSCONTAINERS_CLIB=OFF,"
PACKAGECONFIG[processcontainers_crun] = "-DPROCESSCONTAINERS_CRUN=ON,-DPROCESSCONTAINERS_CRUN=OFF,crun,crun"
PACKAGECONFIG[processcontainers_dobby] = "-DPROCESSCONTAINERS_DOBBY=ON,-DPROCESSCONTAINERS_DOBBY=OFF,dobby"
PACKAGECONFIG[processcontainers_lxc] = "-DPROCESSCONTAINERS_LXC=ON,-DPROCESSCONTAINERS_LXC=OFF,lxc"
PACKAGECONFIG[processcontainers_runc] = "-DPROCESSCONTAINERS_RUNC=ON,-DPROCESSCONTAINERS_RUNC=OFF,runc-docker"

PACKAGECONFIG[securesocket] = "-DSECURE_SOCKET=ON,-DSECURE_SOCKET=OFF,openssl"
PACKAGECONFIG[virtualinput] = "-DVIRTUALINPUT=ON,-DVIRTUALINPUT=OFF,"
PACKAGECONFIG[wcharsupport] = "-DWCHAR_SUPPORT=ON,-DWCHAR_SUPPORT=OFF,"

PACKAGECONFIG[cyclicinspector] = "-DTEST_CYCLICINSPECTOR=ON,-DTEST_CYCLICINSPECTOR=OFF,"
PACKAGECONFIG[hidenonexternalsymbols] = "-DHIDE_NON_EXTERNAL_SYMBOLS=ON,-DHIDE_NON_EXTERNAL_SYMBOLS=OFF,"
PACKAGECONFIG[performancemonitor] = "-DPERFORMANCE_MONITOR=ON,-DPERFORMANCE_MONITOR=OFF,"
PACKAGECONFIG[profiler] = "-DPROFILER=ON,-DPROFILER=OFF,"
PACKAGECONFIG[strictcompilersettings] = "-DENABLE_STRICT_COMPILER_SETTINGS=ON,-DENABLE_STRICT_COMPILER_SETTINGS=OFF,"
PACKAGECONFIG[testloader] = "-DTEST_LOADER=ON,-DTEST_LOADER=OFF,"

PACKAGECONFIG[deadlockdetection] = "-DDEADLOCK_DETECTION=ON,-DDEADLOCK_DETECTION=OFF,"
PACKAGECONFIG[disabletracing] = "-DDISABLE_TRACING=ON,-DDISABLE_TRACING=OFF,"
PACKAGECONFIG[exceptionhandling] = "-DEXCEPTIONS_ENABLE=ON,-DEXCEPTIONS_ENABLE=OFF,"
PACKAGECONFIG[exceptioncatching] = "-DEXCEPTION_CATCHING=ON,-DEXCEPTION_CATCHING=OFF,"
PACKAGECONFIG[warningreporting] = "-DWARNING_REPORTING=ON,-DWARNING_REPORTING=OFF,"

PACKAGECONFIG[messaging] = "\
    -DTRACING=OFF \
    -DMESSAGING=ON \
    , -DTRACING=ON -DENABLED_TRACING_LEVEL=${WPEFRAMEWORK_TRACING_LEVEL} \
    -DMESSAGING=OFF, \
"

PACKAGECONFIG[initscriptsupport] = "\
    -DENABLE_INITSCRIPT_SUPPORT=ON \
    -DSYSTEMD_SERVICE="${WPEFRAMEWORK_INITSCRIPT_SYSTEMD_SERVICE}" \
    -DSYSTEM_ROOT_PATH="${WPEFRAMEWORK_INITSCRIPT_SYSTEM_ROOT_PATH}" \
    -DSYSTEMD_EXTRA_DEPENDS="${WPEFRAMEWORK_INITSCRIPT_SYSTEMD_EXTRA_DEPENDS}" \
    -DSYSTEMD_PATH="${systemd_unitdir}" \
    , -DENABLE_INITSCRIPT_SUPPORT=OFF \
"

# FIXME
# The WPEFramework also needs limited Plugin info in order to determine what to put in the "resumes" configuration
# it feels a bit the other way around but lets set at least webserver and webkit
PACKAGECONFIG[webserver_autoresume] = "-DPLUGIN_WEBSERVER=ON,-DPLUGIN_WEBSERVER=OFF,"
PACKAGECONFIG[webkitbrowser_autoresume] = "-DPLUGIN_WEBKITBROWSER=ON,-DPLUGIN_WEBKITBROWSER=OFF,"

# FIXME, determine this a little smarter
# Provision event is required for libprovision and provision plugin
# Network is provided by the Network control plugin
# Location event is required for locationsync plugin
# Time event is required for timesync plugin
# Identifier event is required for Compositor plugin
# Internet event is provided by the LocationSync plugin
# WebSource event is provided by the WebServer plugin

# Only enable certain events if wpeframework is in distro features
WPEFRAMEWORK_DIST_EVENTS ??= "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'thunder', 'Network Time', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'compositor', 'Platform Graphics', '', d)} \
"

WPEFRAMEWORK_EXTERN_EVENTS ??= "\
    ${@bb.utils.contains('PACKAGECONFIG', 'bluetooth', 'Bluetooth', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 'Decryption', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'provisioning', 'Provisioning', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'webserver', 'WebSource', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'securityagent', 'Security', '', d)} \
    ${WPEFRAMEWORK_DIST_EVENTS} \
    Location Internet Identifier \
"

def getlayerrevision(d):
    topdir = d.getVar('TOPDIR', d, True)

    layers = (d.getVar("BBLAYERS", d, True) or "").split()
    for layer in layers:
        my_layer = layer.split('/')[-1]
        if my_layer == 'meta-wpe':
            return base_get_metadata_git_revision(layer, None)

    return "unknown"

WPE_LAYER_REV ??= "${@getlayerrevision(d)}"

EXTRA_OECMAKE += "\
    -DEXTERN_EVENTS="${WPEFRAMEWORK_EXTERN_EVENTS}" \
    -DBUILD_SHARED_LIBS=ON \
    -DRPC=ON \
    -DBUILD_REFERENCE="${SRCREV}" \
    -DTREE_REFERENCE="${WPE_LAYER_REV}" \
    -DPERSISTENT_PATH="${WPEFRAMEWORK_PERSISTENT_PATH}" \
    ${@oe.utils.conditional('WPEFRAMEWORK_VOLATILE_PATH', '', '', '-DVOLATILE_PATH="${WPEFRAMEWORK_VOLATILE_PATH}"', d)} \
    ${@oe.utils.conditional('WPEFRAMEWORK_DATA_PATH', '', '', '-DDATA_PATH="${WPEFRAMEWORK_DATA_PATH}"', d)} \
    -DSYSTEM_PREFIX="${WPEFRAMEWORK_SYSTEM_PREFIX}" \
    -DPORT="${WPEFRAMEWORK_PORT}" \
    -DETHERNETCARD_NAME="${WPEFRAMEWORK_ETHERNETCARD_NAME}" \
    -DIDLE_TIME="${WPEFRAMEWORK_IDLE_TIME}" \
    -DSOFT_KILL_CHECK_WAIT_TIME="${WPEFRAMEWORK_SOFT_KILL_CHECK_WAIT_TIME}" \
    -DHARD_KILL_CHECK_WAIT_TIME="${WPEFRAMEWORK_HARD_KILL_CHECK_WAIT_TIME}" \
    ${@oe.utils.conditional('WPEFRAMEWORK_GROUP', '', '', '-DGROUP="${WPEFRAMEWORK_GROUP}"', d)} \
    -DPYTHON_EXECUTABLE="${PYTHON}""

do_install_append() {
    install -d ${D}${nonarch_base_libdir}/udev/rules.d
    install -m 0644  ${S}/../20-video-device-udev.rules.in ${D}${nonarch_base_libdir}/udev/rules.d/20-video-device-udev.rules
    sed -i -e "s|@SUBSYSTEM@|${WPEFRAMEWORK_PLATFORM_VIDEO_SUBSYSTEM}|g" ${D}${nonarch_base_libdir}/udev/rules.d/20-video-device-udev.rules
    sed -i -e "s|@GROUP@|${WPEFRAMEWORK_PLATFORM_VIDEO_DEVICE_GROUP}|g" ${D}${nonarch_base_libdir}/udev/rules.d/20-video-device-udev.rules
}

SYSTEMD_SERVICE_${PN} = "wpeframework.service"

PACKAGES =+ "${PN}-initscript"
FILES_${PN}-initscript = "${sysconfdir}/init.d/wpeframework"
FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so ${datadir}/WPEFramework/* ${PKG_CONFIG_DIR}/*.pc"
FILES_${PN}-dev += "${libdir}/cmake/*"

INITSCRIPT_PACKAGES = "${PN}-initscript"
INITSCRIPT_NAME_${PN}-initscript = "wpeframework"
INITSCRIPT_PARAMS_${PN}-initscript = "defaults ${WPEFRAMEWORK_START} 24"
RRECOMMENDS_${PN} = "${PN}-initscript"

# If WPE Framework is enabled as distro feature, start earlier. Assuming packagegroup-wpe-boot is used and we're in control for the network
WPEFRAMEWORK_START = "${@bb.utils.contains('DISTRO_FEATURES', 'wpeframework', '40', '80', d)}"

INSANE_SKIP_${PN} += "dev-so"
INSANE_SKIP_${PN}-dbg += "dev-so"

RPROVIDES_${PN} += "thunder"

