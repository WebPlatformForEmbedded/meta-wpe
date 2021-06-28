SUMMARY = "Web Platform for Embedded Framework"
DESCRIPTION = "A C++ platform abstraction layer for generic functionality."

require include/wpeframework.inc
require include/wpeframework-common.inc

DEPENDS_append = " zlib virtual/egl wpeframework-tools-native"
DEPENDS_append_libc-musl = " libexecinfo"

PROVIDES += "thunder"

PV = "3.0+gitr${SRCPV}"
SRC_URI_append = " \
    file://wpeframework-init \
    file://wpeframework.service.in \
"

inherit systemd update-rc.d python3native

WPEFRAMEWORK_SYSTEM_PREFIX ??= "WPE"

PACKAGECONFIG ??= "\
    ${@bb.utils.contains('MACHINE_FEATURES', 'bluetooth', 'bluetooth', '', d)} \
    webserver_autoresume webkitbrowser_autoresume \
"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_debug', 'debug', '', d)}"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_debugoptimized', 'debugoptimized', '', d)}"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_production', 'production', '', d)}"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_release', 'release', '', d)}"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_releasesymbols', 'releasesymbols', '', d)}"

# Buildtype
# Maybe we need to couple this to a Yocto feature
PACKAGECONFIG[debug] = "-DBUILD_TYPE=Debug,,"
PACKAGECONFIG[debugoptimized] = "-DBUILD_TYPE=DebugOptimized,,"
PACKAGECONFIG[releasesymbols] = "-DBUILD_TYPE=ReleaseSymbols,,"
PACKAGECONFIG[release] = "-DBUILD_TYPE=Release,,"
PACKAGECONFIG[production] = "-DBUILD_TYPE=Production,,"

PACKAGECONFIG[bluetooth] = "-DBLUETOOTH=ON,-DBLUETOOTH=OFF,bluez5"
PACKAGECONFIG[broadcast] = "-DBROADCAST=ON,-DBROADCAST=OFF,"
PACKAGECONFIG[broadcastsiparse] = "-DBROADCAST_SI_PARSING=ON,-DBROADCAST_SI_PARSING=OFF,"

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
PACKAGECONFIG[performancemonitor] = "-DPERFORMANCE_MONITOR=ON,-DPERFORMANCE_MONITO=OFF,"
PACKAGECONFIG[profiler] = "-DPROFILER=ON,-DPROFILER=OFF,"
PACKAGECONFIG[testloader] = "-DTEST_LOADER=ON,-DTEST_LOADER=OFF,"

PACKAGECONFIG[deadlockdetection] = "-DDEADLOCK_DETECTION=ON,-DDEADLOCK_DETECTION=OFF,"
PACKAGECONFIG[disabletracing] = "-DDISABLE_TRACING=ON,-DDISABLE_TRACING=OFF,"
PACKAGECONFIG[exceptionhandling] = "-DEXCEPTIONS_ENABLE=ON,-DEXCEPTIONS_ENABLE=OFF,"
PACKAGECONFIG[exceptioncatching] = "-DEXCEPTION_CATCHING=ON,-DEXCEPTION_CATCHING=OFF,"
PACKAGECONFIG[warningreporting] = "-DWARNING_REPORTING=ON,-DWARNING_REPORTING=OFF,"

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
    Location Internet \
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
    -DBUILD_REFERENCE=${SRCREV} \
    -DTREE_REFERENCE=${WPE_LAYER_REV} \
    -DPERSISTENT_PATH=${WPEFRAMEWORK_PERSISTENT_PATH} \
    -DSYSTEM_PREFIX=${WPEFRAMEWORK_SYSTEM_PREFIX} \
    -DPYTHON_EXECUTABLE=${PYTHON}"

do_install_append() {
    if ${@bb.utils.contains("DISTRO_FEATURES", "systemd", "true", "false", d)}
    then
        if ${@bb.utils.contains("MACHINE_FEATURES", "platformserver", "true", "false", d)}
        then
           extra_after=""
        elif ${@bb.utils.contains("PREFERRED_PROVIDER_virtual/egl", "broadcom-refsw", "true", "false", d)}
        then
           extra_after="nxserver.service"
        fi
        extra_after="${extra_after} ${WAYLAND_COMPOSITOR}"
        install -d ${D}${systemd_unitdir}/system
        sed -e "s|@EXTRA_AFTER@|${extra_after}|g" < ${WORKDIR}/wpeframework.service.in > ${D}${systemd_unitdir}/system/wpeframework.service
    else
        install -d ${D}${sysconfdir}/init.d
        sed -e "s|WPEFRAMEWORK_PERSISTENT_PATH|${WPEFRAMEWORK_PERSISTENT_PATH}|g" < ${WORKDIR}/wpeframework-init > ${D}${sysconfdir}/init.d/wpeframework
        chmod +x ${D}${sysconfdir}/init.d/wpeframework
    fi
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
