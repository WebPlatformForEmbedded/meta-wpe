# Provides configuration for the WebServer Plugin
WPE_WEBSERVER_AUTOSTART ??= "true"
WPE_WEBSERVER_RESUMED ??= "true"
WPE_WEBSERVER_PORT ??= "8080"
WPE_WEBSERVER_PATH ??= "/var/www/"
WPE_DEVICEINFO_ENABLED ??= "ON"

# -----------------------------------------------------------------------------------------------------------
PACKAGECONFIG[webserver] = "\
    -DPLUGIN_WEBSERVER=ON \
    -DPLUGIN_WEBSERVER_AUTOSTART="${WPE_WEBSERVER_AUTOSTART}" \
    -DPLUGIN_WEBSERVER_RESUMED="${WPE_WEBSERVER_RESUMED}" \
    -DPLUGIN_WEBSERVER_PORT="${WPE_WEBSERVER_PORT}" \
    -DPLUGIN_WEBSERVER_PATH="${WPE_WEBSERVER_PATH}" \
    -DPLUGIN_DEVICEINFO="${WPE_DEVICEINFO_ENABLED}" \
    ,-DPLUGIN_WEBSERVER=OFF, \
"

# -----------------------------------------------------------------------------------------------------------
