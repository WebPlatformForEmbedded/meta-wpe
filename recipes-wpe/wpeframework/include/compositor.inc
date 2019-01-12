# Compositor plugin provides an interface to manage windows on the embedded device. Either through Wayland, dispmanx or Nexus

# Compositor settings, if Wayland is in the distro set the implementation to Wayland with Westeros dependency
WPE_COMPOSITOR ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'compositor', '', d)}"
WPE_COMPOSITOR_IMPL ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'Wayland', 'None', d)}"
WPE_COMPOSITOR_DEP ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'westeros', '', d)}"
WPE_COMPOSITOR_EXTRAFLAGS ?= ""
WPE_COMPOSITOR_HARDWARE_READY ?= "0"

# ----------------------------------------------------------------------------

PACKAGECONFIG[compositor]     = " \
    -DPLUGIN_COMPOSITOR=ON \
    -DPLUGIN_COMPOSITOR_HARDWAREREADY=${WPE_COMPOSITOR_HARDWARE_READY} \
    -DPLUGIN_COMPOSITOR_IMPLEMENTATION=${WPE_COMPOSITOR_IMPL} \
    -DPLUGIN_COMPOSITOR_VIRTUALINPUT=ON ${WPE_COMPOSITOR_EXTRAFLAGS} \
    ,-DPLUGIN_COMPOSITOR=OFF,${WPE_COMPOSITOR_DEP}"

# ----------------------------------------------------------------------------