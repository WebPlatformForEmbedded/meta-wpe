WPE_COBALT_STARBOARD_CONFIGURATION_INCLUDE ?= ""
WPE_COBALT_STARBOARD_CONFIGURATION_INCLUDE_rpi ?= "third_party/starboard/wpe/rpi/configuration_public.h"
WPE_COBALT_STARBOARD_CONFIGURATION_INCLUDE_brcm ?= "third_party/starboard/wpe/brcm/arm/configuration_public.h"

# Additional flags
WPE_COBALT_AUTOSTART        ?= "false"
WPE_COBALT_OUTOFPROCESS     ?= "true"

# ----------------------------------------------------------------------------
WPE_COBALT_EXTRAFLAGS = ' \
    -DPLUGIN_COBALT_AUTOSTART=${WPE_COBALT_AUTOSTART} \
    -DPLUGIN_COBALT_OUTOFPROCESS=${WPE_COBALT_OUTOFPROCESS} \
    -DWPEFRAMEWORK_PLUGIN_COBALT_STARBOARD_CONFIGURATION_INCLUDE=${WPE_COBALT_STARBOARD_CONFIGURATION_INCLUDE} \
'
# ----------------------------------------------------------------------------

PACKAGECONFIG[cobalt] = " \
    -DPLUGIN_COBALT=ON \
    ${WPE_COBALT_EXTRAFLAGS}, \
    -DPLUGIN_COBALT=OFF,libcobalt,libcobalt \
"
# ----------------------------------------------------------------------------

RDEPENDS_${PN} += "libcobalt"
