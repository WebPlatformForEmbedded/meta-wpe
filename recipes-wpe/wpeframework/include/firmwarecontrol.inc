# Additional flags
WPE_FIRMWARECONTROL_AUTOSTART ?= "true"
# ----------------------------------------------------------------------------

PACKAGECONFIG[firmwarecontrol]  = " \
    -DPLUGIN_FIRMWARECONTROL=ON \
    -DPLUGIN_FIRMWARECONTROL_AUTOSTART=${WPE_FIRMWARECONTROL_AUTOSTART} \
    ,-DPLUGIN_FIRMWARECONTROL=OFF,fwupgrade-lib,fwupgrade-lib"

# ----------------------------------------------------------------------------

