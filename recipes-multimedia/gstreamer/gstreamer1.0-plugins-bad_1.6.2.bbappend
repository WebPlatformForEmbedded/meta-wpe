
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0002-mssdemux-PlayReady-WRM-parsing-support.patch \
    file://0003-adaptivedemux-improved-bitrate-estimations.patch \
    file://0004-mssdemux-improved-live-playback-support.patch \
    file://0006-daptivedemux-minimal-HTTP-context-support.patch \
    file://0007-optimize-gl-rpi-wip.patch \
"

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
