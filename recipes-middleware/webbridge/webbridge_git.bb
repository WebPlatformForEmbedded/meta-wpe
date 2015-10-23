SUMMARY = "Metrological's webbridge middleware"
SECTION = "metrological"
HOMEPAGE = "http://www.metrological.com/"
LICENSE = "Proprietary"

LIC_FILES_CHKSUM = "file://README.md;md5=d0a3835874494d05f0d00807cfd8768c"

DEPENDS = "zlib cppsdk"

# Default features, can be overridden. 
WEBBRIDGE_FEATURES ??= "web-ui delayedresponse deviceinfo fancontrol fileserver i2ccontrol provisioning spicontrol systeminformer tempcontol tracecontrol webproxy"

SRCREV = "ada456c749b863baab5f85a92df53822821f8b84"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/webbridge.git;protocol=ssh"

S = "${WORKDIR}/git"

EXTRA_OECMAKE = '\
    -DINSTALL_HEADERS_TO_TARGET=ON \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "debug", "-DWEBBRIDGE_DEBUG=ON", "-DWEBBRIDGE_DEBUG=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "web-ui", "-DWEBBRIDGE_WEB_UI=ON", "-DWEBBRIDGE_WEB_UI=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "browser", "-DWEBBRIDGE_PLUGIN_BROWSER=ON", "-DWEBBRIDGE_PLUGIN_BROWSER=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "delayedresponse", "-DWEBBRIDGE_PLUGIN_DELAYEDRESPONSE=ON", "-DWEBBRIDGE_PLUGIN_DELAYEDRESPONSE=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "deviceinfo", "-DWEBBRIDGE_PLUGIN_DEVICEINFO=ON", "-DWEBBRIDGE_PLUGIN_DEVICEINFO=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "fancontrol", "-DWEBBRIDGE_PLUGIN_FANCONTROL=ON", "-DWEBBRIDGE_PLUGIN_FANCONTROL=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "fileserver", "-DWEBBRIDGE_PLUGIN_FILESERVER=ON", "-DWEBBRIDGE_PLUGIN_FILESERVER=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "i2ccontrol", "-DWEBBRIDGE_PLUGIN_I2CCONTROL=ON", "-DWEBBRIDGE_PLUGIN_I2CCONTROL=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "provisioning", "-DWEBBRIDGE_PLUGIN_PROVISIONING=ON", "-DWEBBRIDGE_PLUGIN_PROVISIONING=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "remotecontrol", "-DWEBBRIDGE_PLUGIN_REMOTECONTROL=ON", "-DWEBBRIDGE_PLUGIN_REMOTECONTROL=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "spicontrol", "-DWEBBRIDGE_PLUGIN_SPICONTROL=ON", "-DWEBBRIDGE_PLUGIN_SPICONTROL=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "surfacecompositor", "-DWEBBRIDGE_PLUGIN_SURFACECOMPOSITOR=ON", "-DWEBBRIDGE_PLUGIN_SURFACECOMPOSITOR=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "systeminformer", "-DWEBBRIDGE_PLUGIN_SYSTEMINFORMER=ON", "-DWEBBRIDGE_PLUGIN_SYSTEMINFORMER=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "tempcontol", "-DWEBBRIDGE_PLUGIN_TEMPCONTROL=ON", "-DWEBBRIDGE_PLUGIN_TEMPCONTROL=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "tracecontrol", "-DWEBBRIDGE_PLUGIN_TRACECONTROL=ON", "-DWEBBRIDGE_PLUGIN_TRACECONTROL=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "webproxy", "-DWEBBRIDGE_PLUGIN_WEBPROXY=ON", "-DWEBBRIDGE_PLUGIN_WEBPROXY=OFF",d)} \
    ${@bb.utils.contains("WEBBRIDGE_FEATURES", "wificontrol", "-DWEBBRIDGE_PLUGIN_WIFICONTROL=ON", "-DWEBBRIDGE_PLUGIN_WIFICONTROL=OFF",d)}\
'

inherit pkgconfig cmake
