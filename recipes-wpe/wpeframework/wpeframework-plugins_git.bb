SUMMARY = "WPE Framework common plugins"
DESCRIPTION = "Common plugins for Thunder framework"
HOMEPAGE = "https://github.com/rdkcentral/ThunderNanoServices"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=47b1321b4411e96bb5aeb94521850d43"

require include/wpeframework-plugins.inc

PV = "3.0+gitr${SRCPV}"
PR = "r1"
RECIPE_BRANCH ?= "master"
SRC_URI = "\
    git://github.com/rdkcentral/ThunderNanoServices.git;protocol=https;branch=${RECIPE_BRANCH} \
    file://index.html \
    file://osmc-devinput-remote.json \
    file://0001-westeros-preload-libwesteros_gl.so.0.0.0.patch \
"
SRCREV ?= "7c1947cfb769d0fac2efc0fb07b152171c901ac1"

# More complicated plugins are moved seperate includes

require include/avs.inc
require include/bluetooth.inc
require include/cobalt.inc
require include/compositor.inc
require include/dhcpserver.inc
require include/dictionary.inc
require include/dialserver.inc
require include/filetransfer.inc
require include/firmwarecontrol.inc
require include/inputswitch.inc
require include/ioconnector.inc
require include/languageadministrator.inc
require include/network.inc
require include/performancemonitor.inc
require include/power.inc
require include/processcontainers.inc
require include/processmonitor.inc
require include/remotecontrol.inc
require include/resourcemonitor.inc
require include/snapshot.inc
require include/spark.inc
require include/streamer.inc
require include/svalbard.inc
require include/switchboard.inc
require include/systemcommands.inc
require include/systemdconnector.inc
require include/timesync.inc
require include/volumecontrol.inc
require include/webproxy.inc
require include/webserver.inc
require include/webshell.inc
require include/wifi.inc

# Added deprecated plugins for backward compatibility
require include/plugins_deprecated.inc

PACKAGECONFIG ??= "\
    ${@bb.utils.contains('MACHINE_FEATURES', 'bluetooth', 'bluetoothcontrol', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'bluetooth', 'bluetoothremote', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'compositor', 'compositor', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemdconnector', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'thunder', 'network', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'wifi', 'network wifi', '', d)} \
    ${@bb.utils.contains('STREAMER_DISTRO_PACKAGE_AVAILABLE', 'True', 'streamer', '', d)} \
    cobalt dhcpserver dictionary ioconnector remote remote-devinput systemcommands timesync webserver \
"

EXTRA_OECMAKE += "\
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
"

do_install_append() {
    if ${@bb.utils.contains("PACKAGECONFIG", "webserver", "true", "false", d)}
    then
      install -d ${D}/var/www
      install -m 0755 ${WORKDIR}/index.html ${D}/var/www/
      install -d ${D}${PLUGIN_WEBSERVER_PATH}
    fi
}

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/* /var/www/index.html"
FILES_${PN} += "${includedir}/WPEFramework/*"
FILES_${PN}-dev += "${libdir}/cmake/*"

INSANE_SKIP_${PN} += "libdir staticdev dev-so"
INSANE_SKIP_${PN}-dbg += "libdir"

