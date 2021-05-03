# Provides configuration for the SecurityAgent Plugin
WPE_SECURITYAGENT_AUTOSTART ??= "true"

# -----------------------------------------------------------------------------------------------------------
PACKAGECONFIG[securityagent] = "\
    -DPLUGIN_SECURITYAGENT=ON \
    -DPLUGIN_SECURITYAGENT_AUTOSTART="${WPE_SECURITYAGENT_AUTOSTART}" \
    ,-DPLUGIN_SECURITYAGENT=OFF, \
"

# -----------------------------------------------------------------------------------------------------------
