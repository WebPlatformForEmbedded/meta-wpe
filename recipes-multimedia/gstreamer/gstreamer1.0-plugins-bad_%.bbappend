
# Enable extra options for rpi. Fixme, needs review.
# We need to define a new PACKAGECONFIG option because --enable-egl appended to
# EXTRA_OECONF would get over-ridden by the "--disable-egl" provided by
# PACKAGECONFIG[wayland] (since config options added by PACKAGECONFIG appear on
# the final configure command line after options appended directly to
# EXTRA_OECONF).

PACKAGECONFIG_append_rpi = " zzrpiextras"
PACKAGECONFIG[zzrpiextras] = "--enable-gles2 --enable-egl --enable-gl --enable-dispmanx,,virtual/libgles2 virtual/egl"

PACKAGECONFIG_append_rpi = " hls libmms faad"


# gst-plugins-package.inc adds a false run-time dependency on perl for
# all gstreamer plug-in ${PN}-apps packages.

RDEPENDS_${PN}-apps_remove = "perl"

# By default gstreamer1.0-plugins-bad enables DirectFB support based on
# DISTRO_FEATURES. Over-ride that and unconditionally disable DirectFB.

PACKAGECONFIG_remove = "directfb"

# Remove orc - causes instability with 1.7.1
# (ok with 1.6.3, but disable everywhere for consistency)

PACKAGECONFIG_remove = "orc"

# Remove rsvg - not required and pulls in gdk-pixbuf, which doesn't
# build with musl libc

PACKAGECONFIG_remove = "rsvg"

# Remove sbc and sndfile - neither is required, but both add a dependency on
# libsndfile1, which then adds dependencies on ogg, flac and vorbis

PACKAGECONFIG_remove = "sbc sndfile"

# Remove uvch264 - not required and causes build problems because the 'libgudev'
# dependency only works for oe-core jethro (in oe-core fido the dependency was
# 'udev').

PACKAGECONFIG_remove = "uvch264"

# WPE depends on an MP3 decoder to support "audio beeps" as focus switches
# between elements in a browser based UI. Note that the mpg123 plug-in is not
# enabled by default as the mpg123 recipe is not in oe-core (as of jethro it's
# in meta-oe) and requires whitelisting as it sets the commercial license flag.
# Enabling mpg123 here is therefore an experimental change, to be reviewed...
#
# Note: the mpg123 plugin was moved to gstreamer1.0-plugins-ugly in 1.7.2. For
# 1.7.2 and later, the mpg123 PACKAGECONFIG should be enabled for
# gstreamer1.0-plugins-ugly instead.

PACKAGECONFIG_append = " mpg123"

# With 1.8.2 (and maybe before), the dvb plugin depends on FEC_2_5 and QAM_4_NR
# being defined in the kernel header file include/linux/dvb/frontend.h
# However these definitions are not found in Linux 3.3.8 (now used for MIPS
# targets), which causes the gstreamer1.0-plugins-bad builds to fail.

EXTRA_OECONF_remove = "--enable-dvb"
EXTRA_OECONF_append = " --disable-dvb"
