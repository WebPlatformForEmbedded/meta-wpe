# Provides configuration for the VolumeControl Plugin
WPE_VOLUMECONTROL_AUTOSTART ??= "true"

PACKAGECONFIG[volumecontrol] = "\
    -DPLUGIN_VOLUMECONTROL=ON \
    -DPLUGIN_VOLUMECONTROL_AUTOSTART="${WPE_VOLUMECONTROL_AUTOSTART}" \
    ,-DPLUGIN_VOLUMECONTROL=OFF, \
"

PACKAGECONFIG[volumecontrol_rdkhal] = "-DRDK_AUDIO_HAL=ON,-DRDK_AUDIO_HAL=OFF,"
