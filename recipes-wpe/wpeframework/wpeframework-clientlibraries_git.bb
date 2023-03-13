SUMMARY = "WPEFramework client libraries"
DESCRIPTION = "Thunder client libraries component"
HOMEPAGE = "https://github.com/rdkcentral/ThunderClientLibraries"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=847677038847363222ffb66cfa6406c2"

DEPENDS_append = " wpeframework-interfaces"

require include/wpeframework.inc
require include/compositor.inc

PR = "r0"
PV = "3.0+gitr${SRCPV}"
RECIPE_BRANCH ?= "master"

SRC_URI = "git://github.com/rdkcentral/ThunderClientLibraries.git;protocol=git;branch=${RECIPE_BRANCH};protocol=https"
SRCREV ?= "5aca10c50eb7f82fb05fe7d62b0e4756cf0f631d"

inherit python3native
WPE_CDMI_ADAPTER_IMPL ??= "${@bb.utils.contains('DISTRO_FEATURES', 'nexus_svp', 'opencdmi_brcm_svp', 'opencdm_gst', d)}"

PACKAGECONFIG ??= "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 'opencdm ${WPE_CDMI_ADAPTER_IMPL}', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'provisioning', 'provisionproxy', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'security', 'securityagent', '', d)} \
    virtualinput \
"

PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'compositor', 'compositorclient', '', d)}"
WPE_INCLUDE_SOFTWARE_CRYPTOGRAPHY_LIBRARY ??= "OFF"
PACKAGECONFIG[cryptography] = "\
    -DCRYPTOGRAPHY=ON \
    -DINCLUDE_SOFTWARE_CRYPTOGRAPHY_LIBRARY="${WPE_INCLUDE_SOFTWARE_CRYPTOGRAPHY_LIBRARY}" \
    ,-DCRYPTOGRAPHY=OFF, \
"

PACKAGECONFIG[bluetoothaudiosink] = "-DBLUETOOTHAUDIOSINK=ON,-DBLUETOOTHAUDIOSINK=OFF,bluez5"
PACKAGECONFIG[cryptography_thunder] = "-DCRYPTOGRAPHY_IMPLEMENTATION="Thunder",,"
PACKAGECONFIG[cryptography_openssl] = "-DCRYPTOGRAPHY_IMPLEMENTATION="OpenSSL",,"
PACKAGECONFIG[cryptography_test] = "-DBUILD_CRYPTOGRAPHY_TESTS=ON,,"

PACKAGECONFIG[deviceinfo] = "-DDEVICEINFO=ON,-DDEVICEINFO=OFF,"
PACKAGECONFIG[displayinfo] = "-DDISPLAYINFO=ON,-DDISPLAYINFO=OFF,"
PACKAGECONFIG[gstreamerclient] = "-DGSTREAMERCLIENT=ON,-DGSTREAMERCLIENT=OFF,gstreamer1 gst1-plugins-base"
PACKAGECONFIG[gstreamerclient_rpi] = "-DPLUGIN_COMPOSITOR_IMPLEMENTATION='RPI',,"

PACKAGECONFIG[playerinfo] = "-DPLAYERINFO=ON,-DPLAYERINFO=OFF,"
PACKAGECONFIG[provisionproxy] = "-DPROVISIONPROXY=ON,-DPROVISIONPROXY=OFF,libprovision"
PACKAGECONFIG[securityagent] = "-DSECURITYAGENT=ON,-DSECURITYAGENT=OFF"
PACKAGECONFIG[virtualinput] = "-DVIRTUALINPUT=ON,-DVIRTUALINPUT=OFF,"

# OCDM
PACKAGECONFIG[opencdm] = "-DCDMI=ON,-DCDMI=OFF,"
PACKAGECONFIG[opencdm_gst] = "-DCDMI_ADAPTER_IMPLEMENTATION=gstreamer,,gstreamer1.0"

EXTRA_OECMAKE += "\
    -DBUILD_SHARED_LIBS=ON \
    -DBUILD_REFERENCE=${SRCREV} \
    -DPYTHON_EXECUTABLE=${PYTHON} \
"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so ${datadir}/WPEFramework/* ${PKG_CONFIG_DIR}/*.pc"
FILES_${PN}-dev += "${libdir}/cmake/*"

INSANE_SKIP_${PN} += "dev-so"
INSANE_SKIP_${PN}-dbg += "dev-so"

# Avoid settings ADNEEDED in LDFLAGS as this can cause the libcompositor.so to drop linking to libEGL/libGLES
# which might not be needed at first glance but will cause problems higher up in the change, there for lets drop -Wl,--as-needed
# some distros, like POKY (morty) enable --as-needed by default (e.g. https://git.yoctoproject.org/cgit/cgit.cgi/poky/tree/meta/conf/distro/include/as-needed.inc?h=morty)
ASNEEDED = ""

