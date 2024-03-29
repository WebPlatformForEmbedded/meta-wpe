# Provides configuration for the MessageControl Plugin
WPE_MESSAGECONTROL_AUTOSTART ??= "true"
WPE_MESSAGECONTROL_ABBREVIATED ??= "true"
WPE_MESSAGECONTROL_MAX_EXPORTCONNECTIONS ??= "5"
WPE_MESSAGECONTROL_REMOTE ??= "false"
WPE_MESSAGECONTROL_PORT ??= "0"
WPE_MESSAGECONTROL_BINDING ??= "0.0.0.0"


PACKAGECONFIG[messagecontrol] = "\
    -DPLUGIN_MESSAGECONTROL=ON \
    -DPLUGIN_MESSAGECONTROL_AUTOSTART="${WPE_MESSAGECONTROL_AUTOSTART}" \
    -DPLUGIN_MESSAGECONTROL_ABBREVIATED="${WPE_MESSAGECONTROL_ABBREVIATED}" \
    -DPLUGIN_MESSAGECONTROL_MAX_EXPORTCONNECTIONS="${WPE_MESSAGECONTROL_MAX_EXPORTCONNECTIONS}" \
    -DPLUGIN_MESSAGECONTROL_REMOTE="${WPE_MESSAGECONTROL_REMOTE}" \
    -DPLUGIN_MESSAGECONTROL_PORT="${WPE_MESSAGECONTROL_PORT}" \
    -DPLUGIN_MESSAGECONTROL_BINDING="${WPE_MESSAGECONTROL_BINDING}" \
    ,-DPLUGIN_MESSAGECONTROL=OFF, \
"

