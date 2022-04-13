SUMMARY = " Gstreamer plugin for decryption of common encryption data."
DESCRIPTION = "Gstreamer plugin that leverages the OpenCDM for decryption \
    of common encryption assets."
HOMEPAGE = "https://github.com/WebPlatformForEmbedded/gstcencdecryptor"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b5b0a376d65d47d649918a3b70e90857"

DEPENDS = " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    wpeframework \
    wpeframework-clientlibraries \
    curl \
"

PV = "0.1.gitr${SRCPV}"

SRC_URI = "git://github.com/WebPlatformForEmbedded/gstcencdecryptor.git;protocol=https;branch=master"
SRCREV = "dad6b3e32783f8c127eddf824813d8e9725f579f"

S = "${WORKDIR}/git"

inherit cmake

FILES:${PN} = "${libdir}/gstreamer-1.0/libgstcencdecrypt.so"

# Fixme, something is pointing to a non-symlink and that pulls in -dev packages
INSANE_SKIP:${PN} = "dev-deps"

