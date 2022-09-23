SUMMARY = " Gstreamer plugin for decryption of common encryption data."
DESCRIPTION = "Gstreamer plugin that leverages the OpenCDM for decryption \
    of common encryption assets."
HOMEPAGE = "https://github.com/WebPlatformForEmbedded/gstcencdecryptor"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b5b0a376d65d47d649918a3b70e90857"

DEPENDS = " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    wpeframework-tools-native \
    wpeframework \
    wpeframework-clientlibraries \
    curl \
"

PV = "0.1.gitr${SRCPV}"

RECIPE_BRANCH ?= "master"
SRC_URI = "git://github.com/WebPlatformForEmbedded/gstcencdecryptor.git;protocol=https;branch=${RECIPE_BRANCH}"
SRCREV ?= "0b11ffa67bdd8fdf9234a996a309860ec7744329"

S = "${WORKDIR}/git"

inherit cmake

FILES_${PN} = "${libdir}/gstreamer-1.0/libgstcencdecrypt.so"

# Fixme, something is pointing to a non-symlink and that pulls in -dev packages
INSANE_SKIP_${PN} = "dev-deps"

