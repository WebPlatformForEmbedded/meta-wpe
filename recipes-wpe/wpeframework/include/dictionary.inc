# Provides configuration for the Dictionary Plugin
WPE_DICTIONARY_AUTOSTART ??= "false"

# -----------------------------------------------------------------------------------------------------------
PACKAGECONFIG[dictionary] = "\
    -DPLUGIN_DICTIONARY=ON \
    -DPLUGIN_DICTIONARY_AUTOSTART="${WPE_DICTIONARY_AUTOSTART}" \
    ,-DPLUGIN_DICTIONARY=OFF, \
"
# -----------------------------------------------------------------------------------------------------------
