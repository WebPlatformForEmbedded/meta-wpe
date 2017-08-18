
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += " \
    file://0001-Revert-uridecodebin-Use-the-correct-caps-name-for-MS.patch \
"


# gst-plugins-package.inc adds a false run-time dependency on perl for
# all gstreamer plug-in ${PN}-apps packages.

RDEPENDS_${PN}-apps_remove = "perl"

# gstreamer1.0-plugins-base has a false dependency on liboil. A fix is pending
# for oe-core master, but workaround it here too in case we are building with
# an older version of gstreamer1.0-plugins-base.inc (e.g. the jethro release).

DEPENDS_remove = "liboil"

# Remove orc - causes instability with 1.7.1
# (ok with 1.6.3, but disable everywhere for consistency)

PACKAGECONFIG_remove = "orc"

# Remove ivorbis - not required and pulls in a number of extra dependencies
# (e.g. tremor is one of the only recipes which fetches its sources with SVN).

PACKAGECONFIG_remove = "ivorbis"

# Disable all ogg / theora / vorbis plug-ins
# Warning: this only safe if the corresponding plug-ins are also removed from
# the gstreamer1.0-meta-audio and gstreamer1.0-meta-video package groups.

PACKAGECONFIG_remove = "ogg theora vorbis ivorbis"
