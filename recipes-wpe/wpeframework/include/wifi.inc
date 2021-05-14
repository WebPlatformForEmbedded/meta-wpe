# Provides configuration for the WifiControl Plugin
WPE_WIFICONTROL_AUTOSTART ??= "true"
WPE_WIFICONTROL_DEP ??= ""
WPE_WIFICONTROL_AUTOCONNECT ??= "true"
WPE_WIFICONTROL_AUTOCONNECT_RETRYINTERVAL ??= "30"
# -----------------------------------------------------------------------------------------------------------
PACKAGECONFIG[wifi] = "\
    -DPLUGIN_WIFICONTROL=ON \
    -DPLUGIN_WIFICONTROL_AUTOSTART="${WPE_WIFICONTROL_AUTOSTART}" \
    -DPLUGIN_WIFICONTROL_AUTOCONNECT="${WPE_WIFICONTROL_AUTOCONNECT}" \
    -DPLUGIN_WIFICONTROL_AUTOCONNECT_RETRYINTERVAL="${WPE_WIFICONTROL_AUTOCONNECT_RETRYINTERVAL}" \
    ,-DPLUGIN_WIFICONTROL=OFF,,wpa-supplicant ${WPE_WIFICONTROL_DEP} \
"

PACKAGECONFIG[wifi_rdkhal] = "-DPLUGIN_USE_RDK_HAL_WIFI=ON,-DPLUGIN_USE_RDK_HAL_WIFI=OFF,,wifi-hal"
# -----------------------------------------------------------------------------------------------------------
