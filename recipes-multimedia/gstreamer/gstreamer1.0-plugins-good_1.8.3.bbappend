
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0002-qtdemux-PIFF-box-detection-and-minimal-parsing-suppo.patch \
    file://0004-Use-the-tfdt-decode-time-when-it-s-significantly-dif.patch \
    file://0005-souphttpsrc-cookie-jar-and-context-query-support.patch \
"

# gst-plugins-package.inc adds a false run-time dependency on perl for
# all gstreamer plug-in ${PN}-apps packages.

RDEPENDS_${PN}-apps_remove = "perl"

# Remove orc - causes instability with 1.7.1
# (ok with 1.6.3, but disable everywhere for consistency)

PACKAGECONFIG_remove = "orc"

# Remove gdk-pixbuf - not required and doesn't build with musl libc

PACKAGECONFIG_remove = "gdk-pixbuf"

# Remove gudev - not required and causes build problems because the 'libgudev'
# dependency only works for oe-core jethro (in oe-core fido the dependency was
# 'udev'). Note that from the gst-plugins-good configure script, gudev is only
# used for v4l2 device detection.

PACKAGECONFIG_remove = "gudev"

# Disable flac plug-in
# Warning: this only safe if the corresponding plug-in is also removed from
# the gstreamer1.0-meta-audio package group.

PACKAGECONFIG_remove = "flac"

# Remove speex - not required

PACKAGECONFIG_remove = "speex"
