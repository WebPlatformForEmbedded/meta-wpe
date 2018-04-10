
# Enable extra options for rpi. Fixme, needs review.
# We need to define a new PACKAGECONFIG option because --enable-egl appended to
# EXTRA_OECONF would get over-ridden by the "--disable-egl" provided by
# PACKAGECONFIG[wayland] (since config options added by PACKAGECONFIG appear on
# the final configure command line after options appended directly to
# EXTRA_OECONF).

PACKAGECONFIG_append_rpi = " zzrpiextras"
PACKAGECONFIG[zzrpiextras] = "--enable-gles2 --enable-egl --enable-gl --enable-dispmanx,,virtual/libgles2 virtual/egl"

PACKAGECONFIG_append_rpi = " hls libmms faad"

# Enable opus for latest WebKit MSE requirements
PACKAGECONFIG_append = " opusparse"
