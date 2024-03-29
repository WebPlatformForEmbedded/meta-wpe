# Provides configuration for the DeviceIdentification Plugin
WPE_DEVICEIDENTIFICATION_AUTOSTART ??= "true"
WPE_DEVICEIDENTIFICATION_USE_MFR ??= "OFF"
WPE_DEVICEIDENTIFICATION_IMPL ??= ""
WPE_DEVICEIDENTIFICATION_INTERFACE_NAME ??= "eth0"

PACKAGECONFIG[deviceidentification] = "\
    -DPLUGIN_DEVICEIDENTIFICATION=ON \
    -DPLUGIN_DEVICEIDENTIFICATION_AUTOSTART="${WPE_DEVICEIDENTIFICATION_AUTOSTART}" \
    -DPLUGIN_DEVICEIDENTIFICATION_USE_MFR="${WPEFRAMEWORK_DEVICEIDENTIFICATION_USE_MFR}" \
    -DPLUGIN_DEVICEIDENTIFICATION_INTERFACE_NAME="${WPE_DEVICEIDENTIFICATION_INTERFACE_NAME}" \
    ${WPE_DEVICEIDENTIFICATION_IMPL} \
    ,-DPLUGIN_DEVICEIDENTIFICATION=OFF, \
"

