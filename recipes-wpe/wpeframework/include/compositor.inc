# Compositor plugin provides an interface to manage windows on the embedded device. Either through Wayland, dispmanx or Nexus

# Compositor settings, if Wayland is in the distro set the implementation to Wayland with Westeros dependency
WPE_COMPOSITOR_IMPL 		?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'Wayland', 'RPI', d)}"
WPE_COMPOSITOR_DEP 			?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'westeros', '', d)}"
WPE_COMPOSITOR_EXTRAFLAGS 	?= ""
WPE_COMPOSITOR_HW_READY 	?= "0"

# Additional flags
WPE_COMPOSITOR_AUTOSTART 	?= "true"
WPE_COMPOSITOR_OUTOFPROCESS ?= "true"
WPE_COMPOSITOR_RESOLUTION	?= "720p"
WPE_COMPOSITOR_MEMORY_GFX	?= ""
WPE_COMPOSITOR_IRMODE		?= "16"
WPE_COMPOSITOR_SVP			?= "None"

# ----------------------------------------------------------------------------

WPE_COMPOSITOR_EXTRAFLAGS = ' \
-DPLUGIN_COMPOSITOR_AUTOSTART=${WPE_COMPOSITOR_AUTOSTART} \
-DPLUGIN_COMPOSITOR_OUTOFPROCESS=${WPE_COMPOSITOR_OUTOFPROCESS} \
-DPLUGIN_COMPOSITOR_RESOLUTION=${WPE_COMPOSITOR_RESOLUTION} \
-DPLUGIN_COMPOSITOR_MEMORY_GFX=${WPE_COMPOSITOR_MEMORY_GFX} \
-DPLUGIN_COMPOSITOR_IRMODE=${WPE_COMPOSITOR_IRMODE} \
-DPLUGIN_COMPOSITOR_VIRTUALINPUT=ON \
-DPLUGIN_COMPOSITOR_SVP="${WPE_COMPOSITOR_SVP}" \
'

# ----------------------------------------------------------------------------

PACKAGECONFIG[compositor]     = " \
    -DPLUGIN_COMPOSITOR=ON \
    -DPLUGIN_COMPOSITOR_HARDWAREREADY=${WPE_COMPOSITOR_HW_READY} \
    -DPLUGIN_COMPOSITOR_IMPLEMENTATION=${WPE_COMPOSITOR_IMPL} \
    ${WPE_COMPOSITOR_EXTRAFLAGS} \
    ,-DPLUGIN_COMPOSITOR=OFF,${WPE_COMPOSITOR_DEP}"

# ----------------------------------------------------------------------------
