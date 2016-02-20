
# WPE depends on an MP3 decoder to support "audio beeps" as focus switches
# between elements in a browser based UI. Note that the mpg123 plug-in is not
# enabled by default as the mpg123 recipe is not in oe-core (as of jethro it's
# in meta-oe) and requires whitelisting as it sets the commercial license flag.
# Enabling mpg123 here is therefore an experimental change, to be reviewed...
#
# Note: the mpg123 plugin was added to gstreamer1.0-plugins-ugly in 1.7.2. For
# 1.7.1 and earlier, the mpg123 PACKAGECONFIG should be enabled for
# gstreamer1.0-plugins-bad instead.

PACKAGECONFIG_append = " mpg123"
