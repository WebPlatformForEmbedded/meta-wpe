
# WPE depends on an MP3 decoder to support "audio beeps" as focus switches
# between elements in a browser based UI. Note that the mpg123 plug-in is not
# enabled by default as the mpg123 recipe is not in oe-core (as of jethro it's
# in meta-oe) and requires whitelisting as it sets the commercial license flag.
# Enabling mpg123 here is therefore an experimental change, to be reviewed...

DEPENDS += " \
   faad2 \
"

PACKAGECONFIG_append = " mpg123 faad"

# Enable extra options for rpi. Fixme, needs review.
# We need to define a new PACKAGECONFIG option because --enable-egl appended to
# EXTRA_OECONF would get over-ridden by the "--disable-egl" provided by
# PACKAGECONFIG[wayland] (since config options added by PACKAGECONFIG appear on
# the final configure command line after options appended directly to
# EXTRA_OECONF).

PACKAGECONFIG_append_rpi = " zzrpiextras"
PACKAGECONFIG[zzrpiextras] = "--enable-gles2 --enable-egl --enable-gl --enable-dispmanx,,virtual/libgles2 virtual/egl"
