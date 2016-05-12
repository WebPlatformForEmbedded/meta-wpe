SUMMARY = "Metrological's webbridge middleware"
HOMEPAGE = "http://www.metrological.com/"
SECTION = "metrological"
LICENSE = "CLOSED"

DEPENDS = "wpe cppsdk zlib"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/webbridge.git;protocol=ssh"

SRCREV = "d693cd114dbeb250ebcae67f59e7cbe2b7c9afd5"

S = "${WORKDIR}/git"

inherit pkgconfig cmake

# The libprovision prebuilt libs currently support glibc ARM only.
PROVISIONING ?= "provisioning"
PROVISIONING_libc-musl = ""
PROVISIONING_mipsel = ""
PROVISIONING_x86 = ""

SNAPSHOT ?= ""
SNAPSHOT_rpi = "snapshot"

MEMORYPROFILE ?= "128m"
MEMORYPRESSURE ?= "databaseprocess:50m,networkprocess:100m,webprocess:300m,rpcprocess:50m"

PACKAGECONFIG ??= "web-ui remotecontrol deviceinfo monitor ${PROVISIONING} tracecontrol webproxy dailserver webkitbrowser ${SNAPSHOT}"

PACKAGECONFIG[browser]            = "-DWEBBRIDGE_PLUGIN_BROWSER=ON,-DWEBBRIDGE_PLUGIN_BROWSER=OFF,"
PACKAGECONFIG[dailserver]         = "-DWEBBRIDGE_PLUGIN_DIALSERVER=ON,-DWEBBRIDGE_PLUGIN_DIALSERVER=OFF,"
PACKAGECONFIG[debug]              = "-DWEBBRIDGE_DEBUG=ON,-DWEBBRIDGE_DEBUG=OFF,"
PACKAGECONFIG[deviceinfo]         = "-DWEBBRIDGE_PLUGIN_DEVICEINFO=ON,-DWEBBRIDGE_PLUGIN_DEVICEINFO=OFF,"
PACKAGECONFIG[monitor]            = "-DWEBBRIDGE_PLUGIN_MONITOR=ON,DWEBBRIDGE_PLUGIN_MONITOR=OFF,"
PACKAGECONFIG[netflix]            = "-DWEBBRIDGE_PLUGIN_NETFLIX=ON,-DWEBBRIDGE_PLUGIN_NETFLIX=OFF,netflix"
PACKAGECONFIG[provisioning]       = "-DWEBBRIDGE_PLUGIN_PROVISIONING=ON,-DWEBBRIDGE_PLUGIN_PROVISIONING=OFF,libprovision,libprovision"
PACKAGECONFIG[queuecommunicator]  = "-DWEBBRIDGE_PLUGIN_QUEUECOMMUNICATOR=ON,-DWEBBRIDGE_PLUGIN_QUEUECOMMUNICATOR=OFF,"
PACKAGECONFIG[remotecontrol]      = "-DWEBBRIDGE_PLUGIN_REMOTECONTROL=ON,-DWEBBRIDGE_PLUGIN_REMOTECONTROL=OFF,"
PACKAGECONFIG[snapshot]           = "-DWEBBRIDGE_PLUGIN_SNAPSHOT=ON,,userland"
PACKAGECONFIG[tracecontrol]       = "-DWEBBRIDGE_PLUGIN_TRACECONTROL=ON,-DWEBBRIDGE_PLUGIN_TRACECONTROL=OFF,"
PACKAGECONFIG[webproxy]           = "-DWEBBRIDGE_PLUGIN_WEBPROXY=ON,-DWEBBRIDGE_PLUGIN_WEBPROXY=OFF,"
PACKAGECONFIG[webkitbrowser]      = "-DWEBBRIDGE_PLUGIN_WEBKITBROWSER=ON \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_AUTOSTART=true \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_MEMORYPROFILE=${MEMORYPROFILE} \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_MEMORYPRESSURE=${MEMORYPRESSURE} \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_STARTURL="about:blank" \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_MEDIADISKCACHE=n \
    ,-DWEBBRIDGE_PLUGIN_WEBKITBROWSER=OFF,wpe"
PACKAGECONFIG[web-ui]             = "-DWEBBRIDGE_WEB_UI=ON,-DWEBBRIDGE_WEB_UI=OFF,"


EXTRA_OECMAKE += "\
    -DINSTALL_HEADERS_TO_TARGET=ON \
    -DWEBBRIDGE_PORT=80 \
    -DWEBBRIDGE_BINDING="0.0.0.0" \
    -DWEBBRIDGE_IDLE_TIME=180 \
    -DWEBBRIDGE_PERSISTENT_PATH="/root" \
    -DWEBBRIDGE_DATA_PATH="/usr/share/webbridge" \
    -DWEBBRIDGE_SYSTEM_PATH="/usr/lib/webbridge" \
    -DWEBBRIDGE_WEBSERVER_PATH="/boot/www" \
    -DWEBBRIDGE_WEBSERVER_PORT=8080 \
    -DWEBBRIDGE_PROXYSTUB_PATH="/usr/lib/webbridge/proxystubs" \
"

# libproxystubs.so is installed in /usr/lib/webbridge/proxystubs
FILES_${PN}-dbg += "${libdir}/webbridge/proxystubs/.debug"

# libWPEInjectedBundle.so is installed in /usr/share/webbridge/WebKitBrowser
FILES_${PN}-dbg += "${datadir}/webbridge/WebKitBrowser/.debug"

TOOLCHAIN = "gcc"
