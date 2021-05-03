# Provides configuration for the InputSwitch Plugin
WPE_INPUTSWITCH_AUTOSTART ??= "true"

# -----------------------------------------------------------------------------------------------------------
PACKAGECONFIG[inputswitch] = "\
    -DPLUGIN_INPUTSWITCH=ON \
    -DPLUGIN_INPUTSWITCH_AUTOSTART="${WPE_INPUTSWITCH_AUTOSTART}" \
    ,-DPLUGIN_INPUTSWITCH=OFF, \
"

# -----------------------------------------------------------------------------------------------------------
