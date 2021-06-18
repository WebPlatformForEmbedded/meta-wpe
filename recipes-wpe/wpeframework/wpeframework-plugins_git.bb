SUMMARY = "WPE Framework common plugins"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=47b1321b4411e96bb5aeb94521850d43"
PR = "r1"

require include/wpeframework-plugins.inc

SRC_URI = "\
git://github.com/rdkcentral/ThunderNanoServices.git;protocol=git;branch=master \
    file://index.html \
    file://osmc-devinput-remote.json \
    file://0001-westeros-preload-libwesteros_gl.so.0.0.0.patch \
"

SRCREV = "a06fdb2ba34e29035ceb01431f6d720d1e5b5e01"

# More complicated plugins are moved seperate includes

include include/bluetooth.inc
include include/cobalt.inc
include include/compositor.inc
include include/dhcpserver.inc
include include/dictionary.inc
include include/dialserver.inc
include include/displayinfo.inc
include include/filetransfer.inc
include include/firmwarecontrol.inc
include include/inputswitch.inc
include include/ioconnector.inc
include include/languageadministrator.inc
include include/network.inc
include include/performancemonitor.inc
include include/playerinfo.inc
include include/power.inc
include include/processcontainers.inc
include include/processmonitor.inc
include include/remotecontrol.inc
include include/resourcemonitor.inc
include include/snapshot.inc
include include/streamer.inc
include include/svalbard.inc
include include/systemcommands.inc
include include/systemdconnector.inc
include include/timesync.inc
include include/volumecontrol.inc
include include/webproxy.inc
include include/webserver.inc
include include/webshell.inc
include include/wifi.inc

# Added deprecated plugins for backward compatibility
include include/plugins_deprecated.inc

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

