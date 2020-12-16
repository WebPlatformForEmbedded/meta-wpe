SUMMARY = "Web Platform for Embedded Framework"
HOMEPAGE = "https://github.com/rdkcentral"
SECTION = "wpe"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f1dffbfd5c2eb52e0302eb6296cc3711"
PR = "r0"

require include/wpeframework.inc

DEPENDS = "zlib virtual/egl wpeframework-tools-native"

DEPENDS_append_libc-musl = " libexecinfo"

PV = "3.0+git${SRCPV}"

SRC_URI = "git://github.com/rdkcentral/Thunder.git;protocol=git;branch=master \
           file://wpeframework-init \
           file://wpeframework.service.in \
           "
SRCREV = "7de8fc6e55a40e4936318ad318e7da1ba93ab701"

inherit cmake pkgconfig systemd update-rc.d

WPEFRAMEWORK_SYSTEM_PREFIX = "OE"

PACKAGECONFIG ?= " \
    release \
    ${@bb.utils.contains('MACHINE_FEATURES', 'bluetooth', 'bluetooth', '', d)} \
    websource webkitbrowser \
    "

# Buildtype
# Maybe we need to couple this to a Yocto feature
PACKAGECONFIG[debug]          = "-DBUILD_TYPE=Debug,,"
PACKAGECONFIG[debugoptimized] = "-DBUILD_TYPE=DebugOptimized,,"
PACKAGECONFIG[releasesymbols] = "-DBUILD_TYPE=ReleaseSymbols,,"
PACKAGECONFIG[release]        = "-DBUILD_TYPE=Release,,"
PACKAGECONFIG[production]     = "-DBUILD_TYPE=Production,,"

PACKAGECONFIG[bluetooth]        = "-DBLUETOOTH=ON,-DBLUETOOTH=OFF, bluez5"
PACKAGECONFIG[cyclicinspector]  = "-DTEST_CYCLICINSPECTOR=ON,-DTEST_CYCLICINSPECTOR=OFF,"
PACKAGECONFIG[testloader]       = "-DTEST_LOADER=ON,-DTEST_LOADER=OFF,"

# FIXME
# The WPEFramework also needs limited Plugin info in order to determine what to put in the "resumes" configuration
# it feels a bit the other way around but lets set at least webserver and webkit
PACKAGECONFIG[websource]       = "-DPLUGIN_WEBSERVER=ON,,"
PACKAGECONFIG[webkitbrowser]   = "-DPLUGIN_WEBKITBROWSER=ON,,"

# FIXME, determine this a little smarter
# Provision event is required for libprovision and provision plugin
# Network is provided by the Network control plugin
# Location event is required for locationsync plugin
# Time event is required for timesync plugin
# Identifier event is required for Compositor plugin
# Internet event is provided by the LocationSync plugin
# WebSource event is provided by the WebServer plugin

# Only enable certain events if wpeframework is in distro features
WPEFRAMEWORK_DIST_EVENTS ?= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'thunder', 'Network Time', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'compositor', 'Platform Graphics', '', d)} \
"

WPEFRAMEWORK_EXTERN_EVENTS ?= " \
    ${@bb.utils.contains('PACKAGECONFIG', 'bluetooth', 'Bluetooth', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 'Decryption', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'provisioning', 'Provisioning', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'websource', 'WebSource', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'securityagent', 'Security', '', d)} \
    ${WPEFRAMEWORK_DIST_EVENTS} \
    Location Internet \
"

def getlayerrevision(d):
    topdir = bb.data.getVar('TOPDIR', d, True)

    layers = (bb.data.getVar("BBLAYERS", d, True) or "").split()
    for layer in layers:
        my_layer = layer.split('/')[-1]
        if my_layer == 'meta-wpe':
            return base_get_metadata_git_revision(layer, None)

    return "unknown"

WPE_LAYER_REV ?= "${@getlayerrevision(d)}"

EXTRA_OECMAKE += " \
    -DINSTALL_HEADERS_TO_TARGET=ON \
    -DEXTERN_EVENTS="${WPEFRAMEWORK_EXTERN_EVENTS}" \
    -DBUILD_SHARED_LIBS=ON \
    -DRPC=ON \
    -DVIRTUALINPUT=ON  \
    -DBUILD_REFERENCE=${SRCREV} \
    -DTREE_REFERENCE=${WPE_LAYER_REV}  \
    -DPERSISTENT_PATH=${WPEFRAMEWORK_PERSISTENT_PATH} \
    -DSYSTEM_PREFIX=${WPEFRAMEWORK_SYSTEM_PREFIX} \
    -DEXCEPTIONS_ENABLE=${WPEFRAMEWORK_EXCEPTIONS_ENABLE} \
    -DPYTHON_EXECUTABLE=${STAGING_BINDIR_NATIVE}/python3-native/python3 \
"

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

# ----------------------------------------------------------------------------

PACKAGES =+ "${PN}-initscript"

FILES_${PN}-initscript = "${sysconfdir}/init.d/wpeframework"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so ${datadir}/WPEFramework/* ${PKG_CONFIG_DIR}/*.pc"
FILES_${PN}-dev += "${libdir}/cmake/*"

# ----------------------------------------------------------------------------

INITSCRIPT_PACKAGES = "${PN}-initscript"
INITSCRIPT_NAME_${PN}-initscript = "wpeframework"

# If WPE Framework is enabled as distro feature, start earlier. Assuming packagegroup-wpe-boot is used and we're in control for the network
WPEFRAMEWORK_START = "${@bb.utils.contains('DISTRO_FEATURES', 'wpeframework', '40', '80', d)}"

INITSCRIPT_PARAMS_${PN}-initscript = "defaults ${WPEFRAMEWORK_START} 24"

RRECOMMENDS_${PN} = "${PN}-initscript"

# ----------------------------------------------------------------------------

INSANE_SKIP_${PN} += "dev-so"
INSANE_SKIP_${PN}-dbg += "dev-so"
