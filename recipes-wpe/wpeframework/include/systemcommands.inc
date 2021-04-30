# Provides configuration for the SystemCommands Plugin
WPE_SYSTEMCOMMANDS_AUTOSTART ??= "true"

# -----------------------------------------------------------------------------------------------------------
PACKAGECONFIG[systemcommands] = "\
    -DPLUGIN_SYSTEMCOMMANDS=ON \
    -DPLUGIN_SYSTEMCOMMANDS_AUTOSTART="${WPE_SYSTEMCOMMANDS_AUTOSTART}" \
    ,-DPLUGIN_SYSTEMCOMMANDS=OFF, \
"
# -----------------------------------------------------------------------------------------------------------
