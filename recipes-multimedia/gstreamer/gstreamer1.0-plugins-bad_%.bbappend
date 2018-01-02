# Enable extra options for rpi. Fixme, needs review.

PACKAGECONFIG_append = " gl"
PACKAGECONFIG_append_rpi = " dispmanx hls libmms faad"

PACKAGECONFIG_remove = "vulkan"

PACKAGECONFIG[dispmanx] = "--enable-dispmanx,--disable-dispmanx,"
PACKAGECONFIG[gl] = "--enable-gl,,"
