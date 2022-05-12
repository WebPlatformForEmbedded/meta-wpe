#
#   Copyright (c) 2019 Amazon.com, Inc.
#   Copyright (c) 2019 Luxoft Sweden AB
#
#   SPDX-License-Identifier: Apache-2.0
#

SUMMARY = "An SDK for commercial device makers to integrate Alexa directly into connected products."
HOMEPAGE = "https://developer.amazon.com/en-IN/docs/alexa/avs-device-sdk"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d92e60ee98664c54f68aa515a6169708"

FILESEXTRAPATHS_prepend:= "${THISDIR}/files:"
DEPENDS = "curl nghttp2 sqlite3 openssl rapidjson"

SRC_URI = "git://github.com/alexa/avs-device-sdk.git;protocol=https;nobranch=1"
SRCREV ?= "v${PV}"
S = "${WORKDIR}/git"

# Skip checksum verification
BB_STRICT_CHECKSUM = "0"

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
PORTAUDIO_CMAKE_OPTIONS = "\
    -DPORTAUDIO=ON \
    -DPORTAUDIO_LIB_PATH=${STAGING_DIR_HOST}${libdir}/libportaudio.so \
    -DPORTAUDIO_INCLUDE_DIR=${STAGING_DIR_HOST}${includedir} \
"
PACKAGECONFIG[portaudio] = "${PORTAUDIO_CMAKE_OPTIONS},,portaudio-v19,portaudio-v19"

# WakeWord: Amazon
KWD_CMAKE_OPTIONS = "\
    -DAMAZON_KEY_WORD_DETECTOR=ON \
    -DAMAZON_KEY_WORD_DETECTOR_LIB_PATH="${STAGING_DIR_HOST}${libdir}/libpryon_lite.so" \
    -DAMAZON_KEY_WORD_DETECTOR_INCLUDE_DIR="${STAGING_DIR_HOST}${includedir}" \
    -DTARGET_KWD_LIB=pryon_lite \
"
PACKAGECONFIG[kwd] = "${KWD_CMAKE_OPTIONS},,alexa-pryon-kwd,"

# WakeWord: Sensory
SENSORY_CMAKE_OPTIONS = "\
    -DSENSORY_KEY_WORD_DETECTOR=ON \
    -DSENSORY_KEY_WORD_DETECTOR_LIB_PATH="${STAGING_DIR_HOST}${libdir}/libsnsr.a" \
    -DSENSORY_KEY_WORD_DETECTOR_INCLUDE_DIR="${STAGING_DIR_HOST}${includedir}" \
"
PACKAGECONFIG[sensory] = "${SENSORY_CMAKE_OPTIONS},,truly-handsfree"

# Opus encoding (v1.11 or later)
PACKAGECONFIG[opus] = "-DOPUS=ON,,libopus"

EXTRA_OECMAKE += "\
   -DCMAKE_SKIP_RPATH=TRUE \
   -DACSDK_EMIT_SENSITIVE_LOGS="${AAC_SENSITIVE_LOGS}" \
   -DACSDK_LATENCY_LOG="${AAC_LATENCY_LOGS}" \
   -DPKCS11=OFF \
   -DUSE_DEFAULT_RAPIDJSON=OFF \
"

FILES:${PN} = "${libdir} ${libdir}/*.so"
FILES:${PN}-dev = "${includedir}"

TARGET_CXXFLAGS+= "-Wno-deprecated-copy"
ASNEEDED = ""

