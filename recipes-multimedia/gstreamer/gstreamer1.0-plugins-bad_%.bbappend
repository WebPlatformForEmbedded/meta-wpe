# Enable extra options for rpi. Fixme, needs review.

PACKAGECONFIG_append = " gl faad opusparse"
PACKAGECONFIG_append_rpi = " dispmanx"

PACKAGECONFIG_remove = "vulkan"

PACKAGECONFIG[dispmanx] = "--enable-dispmanx,--disable-dispmanx,"
PACKAGECONFIG[gl] = "--enable-gl,,"
