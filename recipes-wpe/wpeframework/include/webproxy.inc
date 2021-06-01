# Provides configuration for the WebProxy Plugin
WPE_WEBPROXY_AUTOSTART ??= "true"

PACKAGECONFIG[webproxy] = "\
    -DPLUGIN_WEBPROXY=ON \
    -DPLUGIN_WEBPROXY_AUTOSTART="${WPE_WEBPROXY_AUTOSTART}" \
    ,-DPLUGIN_WEBPROXY=OFF, \
"

