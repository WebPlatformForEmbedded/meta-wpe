# Provides configuration for the PerformanceMonitor Plugin
WPE_PERFORMANCEMONITOR_AUTOSTART ??= "true"

# -----------------------------------------------------------------------------------------------------------
PACKAGECONFIG[performancemonitor] = "\
    -DPLUGIN_PERFORMANCEMONITOR=ON \
    -DPLUGIN_PERFORMANCEMONITOR_AUTOSTART="${WPE_PERFORMANCEMONITOR_AUTOSTART}" \
    ,-DPLUGIN_PERFORMANCEMONITOR=OFF, \
"

# -----------------------------------------------------------------------------------------------------------
