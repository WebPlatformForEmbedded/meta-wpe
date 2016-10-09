SUMMARY = "Metrological's webbridge middleware"
HOMEPAGE = "http://www.metrological.com/"
SECTION = "metrological"
LICENSE = "CLOSED"

DEPENDS = "cppsdk zlib"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/webbridge.git;protocol=ssh;branch=stable"

SRCREV = "61bc55fc4df998f43642b6504a467c394e73cfe4"

S = "${WORKDIR}/git"

inherit pkgconfig cmake

# The libprovision prebuilt libs currently support glibc ARM only.
PROVISIONING ?= "provisioning"
PROVISIONING_libc-musl = ""
PROVISIONING_mipsel = ""
PROVISIONING_x86 = ""

SNAPSHOT ?= ""
SNAPSHOT_rpi = "snapshot"

WEBKITBROWSER_AUTOSTART ?= "true"
WEBKITBROWSER_MEDIADISKCACHE ?= "n"
WEBKITBROWSER_MEMORYPRESSURE ?= "databaseprocess:50m,networkprocess:100m,webprocess:300m,rpcprocess:50m"
WEBKITBROWSER_MEMORYPROFILE ?= "128m"
WEBKITBROWSER_STARTURL ?= "about:blank"

PACKAGECONFIG ?= "dailserver deviceinfo monitor ${PROVISIONING} remotecontrol ${SNAPSHOT} tracecontrol webdriver webkitbrowser webproxy web-ui"

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
PACKAGECONFIG[webdriver]          = "-DWEBBRIDGE_PLUGIN_WEBDRIVER=ON,-DWEBBRIDGE_PLUGIN_WEBDRIVER=OFF,webdriver-wpe"
PACKAGECONFIG[webkitbrowser]      = "-DWEBBRIDGE_PLUGIN_WEBKITBROWSER=ON \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_AUTOSTART="${WEBKITBROWSER_AUTOSTART}" \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_MEDIADISKCACHE="${WEBKITBROWSER_MEDIADISKCACHE}" \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_MEMORYPRESSURE="${WEBKITBROWSER_MEMORYPRESSURE}" \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_MEMORYPROFILE="${WEBKITBROWSER_MEMORYPROFILE}" \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_STARTURL="${WEBKITBROWSER_STARTURL}" \
    ,-DWEBBRIDGE_PLUGIN_WEBKITBROWSER=OFF,wpe"
PACKAGECONFIG[webproxy]           = "-DWEBBRIDGE_PLUGIN_WEBPROXY=ON,-DWEBBRIDGE_PLUGIN_WEBPROXY=OFF,"
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

# libinterfaces.so and libproxystubs.so are installed in /usr/lib/webbridge/proxystubs
# Fixme: to avoid the need for packaging workarounds, plug-ins should be installed
# one level below /usr/lib (e.g. /usr/lib/webbridge-proxystubs)
FILES_${PN}-dbg += "${libdir}/webbridge/proxystubs/.debug"

# libWPEInjectedBundle.so is installed in /usr/share/webbridge/WebKitBrowser (WTF!?!)
FILES_${PN}-dbg += "${datadir}/webbridge/WebKitBrowser/.debug/libWPEInjectedBundle.so"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

INSANE_SKIP_${PN} += "libdir"
INSANE_SKIP_${PN}-dbg += "libdir"

TOOLCHAIN = "gcc"
