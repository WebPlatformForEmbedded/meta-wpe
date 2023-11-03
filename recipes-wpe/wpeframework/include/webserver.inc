# Provides configuration for the WebServer Plugin
WPE_WEBSERVER_AUTOSTART ??= "true"
WPE_WEBSERVER_RESUMED ??= "true"
WPE_WEBSERVER_PORT ??= "8080"
WPE_WEBSERVER_PATH ??= "/var/www/"
WPE_DEVICEINFO_ENABLED ??= "ON"
WPE_WEBSERVER_BRIDGE_PORT ??= "8080"
WPE_WEBSERVER_USER ??= ""
WPE_WEBSERVER_GROUP ??= ""


PACKAGECONFIG[webserver] = "\
    -DPLUGIN_WEBSERVER=ON \
    -DPLUGIN_WEBSERVER_AUTOSTART="${WPE_WEBSERVER_AUTOSTART}" \
    -DPLUGIN_WEBSERVER_RESUMED="${WPE_WEBSERVER_RESUMED}" \
    -DPLUGIN_WEBSERVER_PORT="${WPE_WEBSERVER_PORT}" \
    -DPLUGIN_WEBSERVER_PATH="${WPE_WEBSERVER_PATH}" \
    -DPLUGIN_WEBSERVER_BRIDGE_PORT="${WPE_WEBSERVER_BRIDGE_PORT}" \
    -DPLUGIN_DEVICEINFO="${WPE_DEVICEINFO_ENABLED}" \
    -DPLUGIN_WEBSERVER_USER="${WPE_WEBSERVER_USER}" \
    -DPLUGIN_WEBSERVER_GROUP="${WPE_WEBSERVER_GROUP}" \
    ,-DPLUGIN_WEBSERVER=OFF, \
"
