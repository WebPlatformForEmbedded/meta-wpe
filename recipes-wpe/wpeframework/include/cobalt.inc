WPE_COBALT_STARBOARD_CONFIGURATION_INCLUDE ?= ""
WPE_COBALT_STARBOARD_CONFIGURATION_INCLUDE_rpi ?= "third_party/starboard/wpe/rpi/configuration_public.h"
WPE_COBALT_STARBOARD_CONFIGURATION_INCLUDE_brcm ?= "third_party/starboard/wpe/brcm/arm/configuration_public.h"

# Additional flags
WPE_COBALT_AUTOSTART        ?= "false"
WPE_COBALT_OUTOFPROCESS     ?= "true"
WPE_COBALT_USER             ?= ""
WPE_COBALT_GROUP            ?= ""

WPE_COBALT_RESOLUTION       ?= "720p"
WPE_COBALT_RESOLUTION_rpi   ?= "720p"
WPE_COBALT_RESOLUTION_brcm  ?= "1080p"

# ----------------------------------------------------------------------------
WPE_COBALT_EXTRAFLAGS = ' \
    -DPLUGIN_COBALT_AUTOSTART=${WPE_COBALT_AUTOSTART} \
    -DPLUGIN_COBALT_OUTOFPROCESS=${WPE_COBALT_OUTOFPROCESS} \
    -DPLUGIN_COBALT_USER=${WPE_COBALT_USER} \
    -DPLUGIN_COBALT_GROUP=${WPE_COBALT_GROUP} \
    -DWPEFRAMEWORK_PLUGIN_COBALT_STARBOARD_CONFIGURATION_INCLUDE=${WPE_COBALT_STARBOARD_CONFIGURATION_INCLUDE} \
    -DPLUGIN_COBALT_RESOLUTION=${WPE_COBALT_RESOLUTION} \
'
# ----------------------------------------------------------------------------

PACKAGECONFIG[cobalt] = " \
    -DPLUGIN_COBALT=ON \
    ${WPE_COBALT_EXTRAFLAGS}, \
    -DPLUGIN_COBALT=OFF,libcobalt,libcobalt \
"
