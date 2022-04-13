SUMMARY = "OpenMAX IL plugins for GStreamer"
DESCRIPTION = "GStreamer OpenMAX IL wrapper plugin. This plugin wraps available \
OpenMAX IL components and makes them available as standard GStreamer elements."
HOMEPAGE = "http://gstreamer.freedesktop.org/"
SECTION = "multimedia"

LICENSE = "LGPLv2.1"
LICENSE_FLAGS = "commercial"

DEPENDS:append = " gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-bad"

inherit autotools pkgconfig gettext gtk-doc upstream-version-is-even

acpaths = "-I ${S}/common/m4 -I ${S}/m4"

# RPI specific
GSTREAMER_1_0_OMX_TARGET_rpi = "rpi"
GSTREAMER_1_0_OMX_CORE_NAME_rpi = "${libdir}/libopenmaxil.so"

EXTRA_OECONF:append_rpi = " CFLAGS="$CFLAGS -I${STAGING_DIR_TARGET}/usr/include/IL -I${STAGING_DIR_TARGET}/usr/include/interface/vcos/pthreads -I${STAGING_DIR_TARGET}/usr/include/interface/vmcs_host/linux""
# FIXME: convert to PACKAGECONFIG
EXTRA_OECONF:append_rpi = " --disable-examples"

# -------------

GSTREAMER_1_0_OMX_TARGET ?= "bellagio"
GSTREAMER_1_0_OMX_CORE_NAME ?= "${libdir}/libomxil-bellagio.so.0"

EXTRA_OECONF += "--disable-valgrind --with-omx-target=${GSTREAMER_1_0_OMX_TARGET}"

python __anonymous () {
    omx_target = d.getVar("GSTREAMER_1_0_OMX_TARGET", True)
    if omx_target in ['generic', 'bellagio']:
        # Bellagio headers are incomplete (they are missing the OMX_VERSION_MAJOR,#
        # OMX_VERSION_MINOR, OMX_VERSION_REVISION, and OMX_VERSION_STEP macros);
        # appending a directory path to gst-omx' internal OpenMAX IL headers fixes this
        d.appendVar("CFLAGS", " -I${S}/omx/openmax")
    elif omx_target == "rpi":
        # Dedicated Raspberry Pi OpenMAX IL support makes this package machine specific
        d.setVar("PACKAGE_ARCH", d.getVar("MACHINE_ARCH", True))
}

set_omx_core_name() {
    sed -i -e "s;^core-name=.*;core-name=${GSTREAMER_1_0_OMX_CORE_NAME};" "${D}${sysconfdir}/xdg/gstomx.conf"
}

do_install[postfuncs] += "set_omx_core_name"

FILES:${PN} += "${libdir}/gstreamer-1.0/*.so"
FILES:${PN}-dev += "${libdir}/gstreamer-1.0/*.la"
FILES:${PN}-staticdev += "${libdir}/gstreamer-1.0/*.a"

RDEPENDS:${PN} = "libomxil"
