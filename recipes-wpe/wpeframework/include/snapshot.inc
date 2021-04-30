# Provides configuration for the SnapShot Plugin
WPE_SNAPSHOT_AUTOSTART ??= "true"
WPE_SNAPSHOT_DEP ??= ""

# -----------------------------------------------------------------------------------------------------------
PACKAGECONFIG[snapshot] = "\
    -DPLUGIN_SNAPSHOT=ON \
    -DPLUGIN_SNAPSHOT_AUTOSTART="${WPE_SNAPSHOT_AUTOSTART}" \
    ,-DPLUGIN_SNAPSHOT=OFF,libpng ${WPE_SNAPSHOT_DEP} \
"
# -----------------------------------------------------------------------------------------------------------
