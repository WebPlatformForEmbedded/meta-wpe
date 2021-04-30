# Provides configuration for the WebShell Plugin
WPE_WEBSHELL_AUTOSTART ??= "true"

# -----------------------------------------------------------------------------------------------------------
PACKAGECONFIG[webshell] = "\
    -DPLUGIN_WEBSHELL=ON \
    -DPLUGIN_WEBSHELL_AUTOSTART="${WPE_WEBSHELL_AUTOSTART}" \
    ,-DPLUGIN_WEBSHELL=OFF, \
"

# -----------------------------------------------------------------------------------------------------------
