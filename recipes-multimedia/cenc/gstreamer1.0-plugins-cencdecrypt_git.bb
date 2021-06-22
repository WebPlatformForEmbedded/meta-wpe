SUMMARY = " Gstreamer plugin to support basic cenc decryptions"
DESCRIPTION = "Gstreamer plugin to satisfy all your basic cenc decryption need. \
    i.e Decryption of content with a single and multiple encrypted streams "
HOMEPAGE = "https://github.com/WebPlatformForEmbedded/gstcencdecryptor"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b5b0a376d65d47d649918a3b70e90857"

DEPENDS_append = " wpeframework-clientlibraries gstreamer1.0 gstreamer1.0-plugins-base"

PV = "0.1.gitr${SRCPV}"

SRC_URI = "git://github.com/WebPlatformForEmbedded/gstcencdecryptor.git;protocol=git;branch=master"
SRCREV = "2e37ac383694e067bfef3c7f128b0dd9dac0dfdd"

S = "${WORKDIR}/git"

inherit cmake

FILES_${PN} = "${libdir}/gstreamer-1.0/libgstcencdecrypt.so"

# Fixme, something is pointing to a non-symlink and that pulls in -dev packages
INSANE_SKIP_${PN} = "dev-deps"

