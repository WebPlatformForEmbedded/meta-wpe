SUMMARY = "Metrological's webbridge middleware"
HOMEPAGE = "http://www.metrological.com/"
SECTION = "metrological"
LICENSE = "CLOSED"
#LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "cppsdk zlib"

# ----------------------------------------------------------------------------

# Type 0 = Production, 1 = Release, 2 = Debug
WEBBRIDGE_BUILD_TYPE = "0"
WEBBRIDGE_BUILD_MAJOR = "1"
WEBBRIDGE_BUILD_MINOR = "6"
WEBBRIDGE_BUILD_REVISION = "4"

PV = "${WEBBRIDGE_BUILD_MAJOR}.${WEBBRIDGE_BUILD_MINOR}.${WEBBRIDGE_BUILD_REVISION}+${SRCPV}"
BASEPV = "${@ d.getVar('SRCPV', True).replace('AUTOINC+', '')}"

# ----------------------------------------------------------------------------

SRC_URI = "git://git@github.com/Metrological/webbridge.git;protocol=ssh;branch=stable \
           file://0001-guard-execinfo.h-with-__GLIBC__.patch \
           file://webbridge-init \
"

SRCREV = "9d60d8333f06c063512f7d3b5dd7ef8048d628b5"

S = "${WORKDIR}/git"

inherit cmake pkgconfig update-rc.d

# ----------------------------------------------------------------------------

PROVISIONING ?= "provisioning"
PROVISIONING_libc-musl = ""

SNAPSHOT ?= ""
SNAPSHOT_rpi = "snapshot"

WEBKITBROWSER_AUTOSTART ?= "true"
WEBKITBROWSER_MEDIADISKCACHE ?= "false"
WEBKITBROWSER_MEMORYPRESSURE ?= "databaseprocess:50m,networkprocess:100m,webprocess:300m,rpcprocess:50m"
WEBKITBROWSER_MEMORYPROFILE ?= "128m"
WEBKITBROWSER_STARTURL ?= "about:blank"
WEBKITBROWSER_USERAGENT ?= "Mozilla/5.0 (Macintosh, Intel Mac OS X 10_11_4) AppleWebKit/602.1.28+ (KHTML, like Gecko) Version/9.1 Safari/601.5.17"
WEBKITBROWSER_DISKCACHE ?= "0"
WEBKITBROWSER_XHRCACHE ?= "false"

PACKAGECONFIG ?= "dailserver deviceinfo monitor ${PROVISIONING} netflix remotecontrol ${SNAPSHOT} tracecontrol webdriver webkitbrowser webproxy web-ui"

PACKAGECONFIG[browser]            = "-DWEBBRIDGE_PLUGIN_BROWSER=ON,-DWEBBRIDGE_PLUGIN_BROWSER=OFF,"
PACKAGECONFIG[dailserver]         = "-DWEBBRIDGE_PLUGIN_DIALSERVER=ON,-DWEBBRIDGE_PLUGIN_DIALSERVER=OFF,"
PACKAGECONFIG[debug]              = "-DWEBBRIDGE_DEBUG=ON,-DWEBBRIDGE_DEBUG=OFF,"
PACKAGECONFIG[deviceinfo]         = "-DWEBBRIDGE_PLUGIN_DEVICEINFO=ON,-DWEBBRIDGE_PLUGIN_DEVICEINFO=OFF,"
PACKAGECONFIG[monitor]            = "-DWEBBRIDGE_PLUGIN_MONITOR=ON,DWEBBRIDGE_PLUGIN_MONITOR=OFF,"
PACKAGECONFIG[netflix]            = "-DWEBBRIDGE_PLUGIN_NETFLIX=ON,-DWEBBRIDGE_PLUGIN_NETFLIX=OFF,netflix"
PACKAGECONFIG[provisioning]       = "-DWEBBRIDGE_PLUGIN_PROVISIONING=ON,-DWEBBRIDGE_PLUGIN_PROVISIONING=OFF,libprovision,libprovision"
PACKAGECONFIG[queuecommunicator]  = "-DWEBBRIDGE_PLUGIN_QUEUECOMMUNICATOR=ON,-DWEBBRIDGE_PLUGIN_QUEUECOMMUNICATOR=OFF,"
PACKAGECONFIG[remotecontrol]      = "-DWEBBRIDGE_PLUGIN_REMOTECONTROL=ON,-DWEBBRIDGE_PLUGIN_REMOTECONTROL=OFF,"
PACKAGECONFIG[snapshot]           = "-DWEBBRIDGE_PLUGIN_SNAPSHOT=ON,,virtual/mesa"
PACKAGECONFIG[tracecontrol]       = "-DWEBBRIDGE_PLUGIN_TRACECONTROL=ON,-DWEBBRIDGE_PLUGIN_TRACECONTROL=OFF,"
PACKAGECONFIG[youtube]            = "-DWEBBRIDGE_PLUGIN_WEBKITBROWSER_YOUTUBE=ON, -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_YOUTUBE=OFF,"
PACKAGECONFIG[webdriver]          = "-DWEBBRIDGE_PLUGIN_WEBDRIVER=ON,-DWEBBRIDGE_PLUGIN_WEBDRIVER=OFF,webdriver-wpe"
PACKAGECONFIG[webkitbrowser]      = "-DWEBBRIDGE_PLUGIN_WEBKITBROWSER=ON \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_AUTOSTART="${WEBKITBROWSER_AUTOSTART}" \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_MEDIADISKCACHE="${WEBKITBROWSER_MEDIADISKCACHE}" \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_MEMORYPRESSURE="${WEBKITBROWSER_MEMORYPRESSURE}" \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_MEMORYPROFILE="${WEBKITBROWSER_MEMORYPROFILE}" \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_STARTURL="${WEBKITBROWSER_STARTURL}" \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_USERAGENT="${WEBKITBROWSER_USERAGENT}" \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_DISKCACHE="${WEBKITBROWSER_DISKCACHE}" \
    -DWEBBRIDGE_PLUGIN_WEBKITBROWSER_XHRCACHE="${WEBKITBROWSER_XHRCACHE}" \
    ,-DWEBBRIDGE_PLUGIN_WEBKITBROWSER=OFF,wpe"
PACKAGECONFIG[webproxy]           = "-DWEBBRIDGE_PLUGIN_WEBPROXY=ON,-DWEBBRIDGE_PLUGIN_WEBPROXY=OFF,"
PACKAGECONFIG[web-ui]             = "-DWEBBRIDGE_WEB_UI=ON,-DWEBBRIDGE_WEB_UI=OFF,"

EXTRA_OECMAKE += "\
    -DINSTALL_HEADERS_TO_TARGET=ON \
    -DWEBBRIDGE_BUILD_VERSION=${WEBBRIDGE_BUILD_MAJOR}.${WEBBRIDGE_BUILD_MINOR}.${WEBBRIDGE_BUILD_REVISION} \
    -DWEBBRIDGE_BUILD_MAJOR=${WEBBRIDGE_BUILD_MAJOR} \
    -DWEBBRIDGE_BUILD_MINOR=${WEBBRIDGE_BUILD_MINOR} \
    -DWEBBRIDGE_BUILD_REVISION=${WEBBRIDGE_BUILD_REVISION} \
    -DWEBBRIDGE_BUILD_TYPE=${WEBBRIDGE_BUILD_TYPE} \
    -DWEBBRIDGE_BUILD_HASH=${BASEPV} \
    -DWEBBRIDGE_BUILD_REF=${SRCREV} \
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
# ----------------------------------------------------------------------------

do_configure_append() {
    for f in `find ${B} -name "*.make"`; do
        sed -i -e 's#-I${STAGING_INCDIR_NATIVE}##g' $f
    done
}

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/webbridge-init ${D}${sysconfdir}/init.d/webbridge
}

# ----------------------------------------------------------------------------

PACKAGES =+ "${PN}-initscript"

FILES_${PN}-initscript = "${sysconfdir}/init.d/webbridge"

# libinterfaces.so and libproxystubs.so are installed in /usr/lib/webbridge/proxystubs
# Fixme: to avoid the need for packaging workarounds, plug-ins should be installed
# one level below /usr/lib (e.g. /usr/lib/webbridge-proxystubs)
FILES_${PN}-dbg += "${libdir}/webbridge/proxystubs/.debug"

# libWPEInjectedBundle.so is installed in /usr/share/webbridge/WebKitBrowser (WTF!?!)
FILES_${PN}-dbg += "${datadir}/webbridge/WebKitBrowser/.debug/libWPEInjectedBundle.so"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

# ----------------------------------------------------------------------------

INITSCRIPT_PACKAGES = "${PN}-initscript"
INITSCRIPT_NAME_${PN}-initscript = "webbridge"
INITSCRIPT_PARAMS_${PN}-initscript = "defaults 80 24"

RRECOMMENDS_${PN} = "${PN}-initscript"

# ----------------------------------------------------------------------------

INSANE_SKIP_${PN} += "libdir"
INSANE_SKIP_${PN}-dbg += "libdir"

TOOLCHAIN = "gcc"
