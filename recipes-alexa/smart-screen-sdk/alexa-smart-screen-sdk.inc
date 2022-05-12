SUMMARY = "The Alexa Smart Screen SDK extends the AVS Device SDK to support development for screen-based Alexa Built-in products. This SDK enables device makers to build screen-based products that complement Alexa voice responses with rich visual experiences."
HOMEPAGE = "https://developer.amazon.com/en-IN/alexa/devices/alexa-built-in/development-resources/smart-screen-sdk"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=8af6ce427017dadf03f47608d86f3716"

FILESEXTRAPATHS_prepend:= "${THISDIR}/files:"

DEPENDS = "nodejs-native apl-client-library avs-device-sdk rapidjson"

SRC_URI = "git://github.com/alexa/alexa-smart-screen-sdk.git;protocol=https;nobranch=1"
SRCREV ?= "v${PV}"
S = "${WORKDIR}/git"

# Skip checksum verification
BB_STRICT_CHECKSUM = "0"

inherit pkgconfig cmake

PACKAGECONFIG[release] = "-DCMAKE_BUILD_TYPE=RELEASE,-DCMAKE_BUILD_TYPE=DEBUG -DDISABLE_WEBSOCKET_SSL=ON,"

# GStreamer for Media Player
PACKAGECONFIG[gstreamer] = "\
    -DGSTREAMER_MEDIA_PLAYER=ON, \
    -DGSTREAMER_MEDIA_PLAYER=OFF, \
    gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad gstreamer1.0-libav \
"

# GSClient settings
ASS_SDK_JS_GUICLIENT_PATH ??= "var/www/alexa"
PACKAGECONFIG[jsguiclient] = "\
    -DJS_GUICLIENT_ENABLE=ON \
    -DJS_GUICLIENT_INSTALL_PATH="${D}/${ASS_SDK_JS_GUICLIENT_PATH}", \
    -DJS_GUICLIENT_ENABLE=OFF, \
"

# Portaudio for microphone input
PORTAUDIO_CMAKE_OPTIONS = "\
    -DPORTAUDIO=ON \
    -DPORTAUDIO_LIB_PATH="${STAGING_DIR_HOST}${libdir}/libportaudio.so" \
    -DPORTAUDIO_INCLUDE_DIR="${STAGING_DIR_HOST}${includedir}" \
"
PACKAGECONFIG[portaudio] = "${PORTAUDIO_CMAKE_OPTIONS},,portaudio-v19,portaudio-v19"

OECMAKE_GENERATOR = "Unix Makefiles"
EXTRA_OECMAKE += "\
    -DBUILD_TESTING=OFF \
    -DBUILD_SHARED_LIBS=ON \
    -DCMAKE_SKIP_RPATH=TRUE \
    -DAPL_CORE=ON \
    -DCMAKE_SYSROOT="${STAGING_DIR_HOST}" \
    -DCMAKE_PREFIX_PATH="${STAGING_DIR_TARGET}/usr" \
    -DAPL_CLIENT_INSTALL_PATH="${STAGING_DIR_TARGET}/usr" \
    -DWEBSOCKETPP_INCLUDE_DIR="${STAGING_DIR_TARGET}${includedir}/websocketpp" \
    -DAPL_CLIENT_JS_PATH="${STAGING_DIR_TARGET}${datadir}/apl-client-js" \
"

FILES:${PN} = "${libdir} ${libdir}.so ${ASS_SDK_JS_GUICLIENT_PATH}"
FILES:${PN}-dev = "${includedir}"

INSANE_SKIP_${PN} = "ldflags dev-deps"

do_install_append() {
    chrpath -d "${D}${libdir}/libSmartScreenSampleAppLib.so"
}

ASNEEDED = ""
PARALLEL_MAKE = "-j1"

