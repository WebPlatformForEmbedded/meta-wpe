SUMMARY = "WPEFramework rdkservices"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=16cf2209d4e903e4d5dcd75089d7dfe2"
PR = "r1"

require include/wpeframework-plugins.inc

SRC_URI = "git://github.com/rdkcentral/rdkservices.git;protocol=git;branch=master"
SRCREV = "fafde8d9fb3aeca340d6a823d9d6de6f5c60d996"

SRC_URI += "file://0001-remove-default-services.patch"

# ----------------------------------------------------------------------------

WPEFRAMEWORK_LOCATIONSYNC_URI ?= "http://jsonip.metrological.com/?maf=true"

# ----------------------------------------------------------------------------

PACKAGECONFIG ?= " deviceinfo locationsync monitor tracing"

PACKAGECONFIG[deviceinfo]     = "-DENABLE_DEVICE_INFO=ON,-DENABLE_DEVICE_INFO=OFF,"
PACKAGECONFIG[locationsync]   = "-DENABLE_LOCATION_SYNC=ON \
                                 -DPLUGIN_LOCATIONSYNC_URI=${WPEFRAMEWORK_LOCATIONSYNC_URI} \
                                ,-DENABLE_LOCATION_SYNC=OFF,"
PACKAGECONFIG[monitor]        = "-DENABLE_MONITOR=ON \
                                 -DPLUGIN_WEBKITBROWSER=ON \
                                 -DPLUGIN_WEBKITBROWSER_YOUTUBE=ON \
                                 -DPLUGIN_NETFLIX=ON \
                                 -DPLUGIN_WEBKITBROWSER_MEMORYLIMIT=614400 \
                                 -DPLUGIN_YOUTUBE_MEMORYLIMIT=614400 \
                                 -DPLUGIN_NETFLIX_MEMORYLIMIT=307200 \
                                ,-DENABLE_MONITOR=OFF,"
PACKAGECONFIG[tracing]        = "-DENABLE_TRACE_CONTROL=ON,-DENABLE_TRACE_CONTROL=OFF,"

# ----------------------------------------------------------------------------

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DPLUGIN_DISPLAYSETTINGS=OFF \
    -DPLUGIN_LOGGING_PREFERENCES=OFF \
    -DPLUGIN_USER_PREFERENCES=OFF \
    -DPLUGIN_SYSTEMSERVICES=OFF \
    -DPLUGIN_CONTROLSERVICE=OFF \
"

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/*"
FILES_${PN} += "${includedir}/WPEFramework/*"
FILES_${PN}-dev += "${libdir}/cmake/*"

INSANE_SKIP_${PN} += "libdir staticdev dev-so"
INSANE_SKIP_${PN}-dbg += "libdir"
