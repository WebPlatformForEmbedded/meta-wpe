SUMMARY = "AAMP Gstreamer plugin"
DESCRIPTION = "Advanced Adaptive Micro Player GStreamer plugin support playback of HLS and MPEGDash contents"
HOMEPAGE = "https://github.com/rdkcmf/rdk-gst-plugins-rdk-aamp"
LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING.LGPL;md5=2a626f155f0a604febb7d0fc6b0611af"

DEPENDS:append = " aamp gstreamer1.0 gstreamer1.0-plugins-base wpeframework-clientlibraries"

PV = "0.1.gitr${SRCPV}"

SRC_URI = "git://github.com/rdkcmf/rdk-gst-plugins-rdk-aamp.git;protocol=https;branch=stable2"
SRCREV = "c9d03d4df04e0a09a0b8cd45c122c6add12b0501"

S = "${WORKDIR}/git"

inherit cmake

PACKAGECONFIG ??= " ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 'opencdm', '', d)}"
PACKAGECONFIG[opencdm] = "-DCMAKE_DASH_DRM=ON -DCMAKE_USE_OPENCDM_ADAPTER=ON,,"

FILES:${PN} = "${libdir}/gstreamer-1.0/libgstaamp.so"

# Fixme, something is pointing to a non-symlink and that pulls in -dev packages
INSANE_SKIP:${PN} = "dev-deps"

