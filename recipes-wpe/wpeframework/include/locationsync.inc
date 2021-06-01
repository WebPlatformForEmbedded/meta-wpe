# Provides configuration for the LocationSync Plugin
WPE_LOCATIONSYNC_AUTOSTART ??= "true"
WPE_LOCATIONSYNC_URI ??= "http://jsonip.metrological.com/?maf=true"

PACKAGECONFIG[locationsync] = "\
    -DPLUGIN_LOCATIONSYNC=ON \
    -DPLUGIN_LOCATIONSYNC_AUTOSTART="${WPE_LOCATIONSYNC_AUTOSTART}" \
    -DPLUGIN_LOCATIONSYNC_URI="${WPE_LOCATIONSYNC_URI}" \
    ,-DPLUGIN_LOCATIONSYNC=OFF, \
"

