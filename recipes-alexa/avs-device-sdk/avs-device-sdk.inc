SUMMARY = "An SDK for commercial device makers to integrate Alexa directly into connected products."
HOMEPAGE = "https://developer.amazon.com/en-IN/docs/alexa/avs-device-sdk"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d92e60ee98664c54f68aa515a6169708"

FILESEXTRAPATHS_prepend:= "${THISDIR}/files:"
DEPENDS_append = " curl nghttp2 sqlite3 openssl rapidjson"

RECIPE_BRANCH ?= "master"
SRC_URI = "git://github.com/alexa/avs-device-sdk.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "v${PV}"
S = "${WORKDIR}/git"

# Default extra variables
AAC_SENSITIVE_LOGS ??= "OFF"
AAC_LATENCY_LOGS ??= "OFF"
AAC_ENABLE_TESTS ??= "OFF"

inherit pkgconfig cmake

PACKAGECONFIG[release] = "-DCMAKE_BUILD_TYPE=RELEASE,-DCMAKE_BUILD_TYPE=DEBUG,"

# GStreamer for Media Player
PACKAGECONFIG[gstreamer] = "\
    -DGSTREAMER_MEDIA_PLAYER=ON, \
    -DGSTREAMER_MEDIA_PLAYER=OFF, \
    gstreamer1.0 gstreamer1.0-plugins-base \
"

# Portaudio for microphone input
PACKAGECONFIG[portaudio] = "\
    -DPORTAUDIO=ON \
    -DPORTAUDIO_LIB_PATH=${STAGING_DIR_HOST}${libdir}/libportaudio.so \
    -DPORTAUDIO_INCLUDE_DIR=${STAGING_DIR_HOST}${includedir} \
    ,portaudio-v19,portaudio-v19 \
"

# Opus encoding (v1.11 or later)
PACKAGECONFIG[opus] = "-DOPUS=ON,,libopus"


PACKAGECONFIG ??= " gstreamer portaudio"

EXTRA_OECMAKE += "\
   -DCMAKE_SKIP_RPATH=TRUE \
   -DACSDK_EMIT_SENSITIVE_LOGS="${AAC_SENSITIVE_LOGS}" \
   -DACSDK_LATENCY_LOG="${AAC_LATENCY_LOGS}" \
   -DPKCS11=OFF \
   -DUSE_DEFAULT_RAPIDJSON=OFF \
"

FILES_${PN}  = "${libdir}"
FILES_${PN}-dev = "${includedir}"

CXXFLAGS_append = " -Wno-deprecated-copy"

