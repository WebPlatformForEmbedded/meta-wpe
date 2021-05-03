# Provides configuration for the ResourceMonitor Plugin
WPE_RESOURCEMONITOR_AUTOSTART ??= "false"
WPE_RESOURCEMONITOR_RESUMED ??= "true"
WPE_RESOURCEMONITOR_OUTOFPROCESS ??= "true"

# -----------------------------------------------------------------------------------------------------------
PACKAGECONFIG[resourcemonitor] = "\
    -DPLUGIN_RESOURCEMONITOR=ON \
    -DPLUGIN_RESOURCEMONITOR_AUTOSTART="${WPE_RESOURCEMONITOR_AUTOSTART}" \
    -DPLUGIN_RESOURCEMONITOR_RESUMED="${WPE_RESOURCEMONITOR_RESUMED}" \
    -DPLUGIN_RESOURCEMONITOR_OUTOFPROCESS="${WPE_RESOURCEMONITOR_OUTOFPROCESS}" \
    ,-DPLUGIN_RESOURCEMONITOR=OFF, \
"

# -----------------------------------------------------------------------------------------------------------
