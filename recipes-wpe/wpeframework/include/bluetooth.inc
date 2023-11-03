# Provides configuration for the BluetoothControl and BluetoothRemoteControl Plugins
WPE_BLUETOOTH_AUTOSTART ??= "true"
WPE_BLUETOOTHAUDIO_AUTOSTART ??= "true"
WPE_BLUETOOTHSDPSERVER_AUTOSTART ??= "true"
WPE_BLUETOOTHREMOTECONTROL_AUTOSTART ??= "true"
DPLUGIN_BLUETOOTHAUDIOSINK_AUTOSTART ??= "true"
WPE_BLUETOOTH_PERSISTMAC ??= "false"
WPE_BLUETOOTH_DEPENDENCIES ??= "wpeframework-libraries"
WPE_BLUETOOTH_CONTROLLER ??= "BluetoothControl"
WPE_BLUETOOTHSDPSERVER_PROVIDER ??= "Thunder"
WPE_BLUETOOTHAUDIO_SINK_LATENCY ??= "20"
WPE_BLUETOOTHAUDIO_SINK_CODECSBC_PRESET ??= "HQ"
WPE_BLUETOOTHAUDIO_SOURCE_CODECSBC_MAXBITPOOL ??= "53"
WPE_BLUETOOTHSDPSERVER_SERVICE_A2DP_AUDIO ??= "BluetoothAudio"
WPE_BLUETOOTHSDPSERVER_SERVICE_A2DPAUDIOSINK_NAME ??= "A2DP Audio Source"
WPE_BLUETOOTHSDPSERVER_SERVICE_A2DPAUDIOSINK_DESCRIPTION ??= "Thunder BluetoothAudio"
WPE_BLUETOOTHSDPSERVER_SERVICE_A2DPAUDIOSOURCE_NAME ??= "A2DP Audio Sink"
WPE_BLUETOOTHSDPSERVER_SERVICE_A2DPAUDIOSOURCE_DESCRIPTION ??= "Thunder BluetoothAudio Plugin"


PACKAGECONFIG[bluetoothcontrol] = "\
    -DPLUGIN_BLUETOOTH=ON \
    -DPLUGIN_BLUETOOTH_AUTOSTART="${WPE_BLUETOOTH_AUTOSTART}" \
    -DPLUGIN_BLUETOOTH_PERSISTMAC="${WPE_BLUETOOTH_PERSISTMAC}" \
    ,-DPLUGIN_BLUETOOTH=OFF \
    ,${WPE_BLUETOOTH_DEPENDENCIES},bluez5 \
"

PACKAGECONFIG[bluetoothremote] = "\
    -DPLUGIN_BLUETOOTHREMOTECONTROL=ON \
    -DPLUGIN_BLUETOOTHREMOTECONTROL_CONTROLLER="${WPE_BLUETOOTH_CONTROLLER}" \
    -DPLUGIN_BLUETOOTHREMOTECONTROL_AUTOSTART="${WPE_BLUETOOTHREMOTECONTROL_AUTOSTART}" \
    ,-DPLUGIN_BLUETOOTHREMOTECONTROL=OFF, \
"

PACKAGECONFIG[bluetoothaudiosink] = "\
    -DPLUGIN_BLUETOOTHAUDIOSINK=ON \
    -DPLUGIN_BLUETOOTHAUDIOSINK_AUTOSTART="${WPE_BLUETOOTHAUDIOSINK_AUTOSTART}" \
   ,-DPLUGIN_BLUETOOTHAUDIOSINK=OFF, \
"

PACKAGECONFIG[bluetoothaudio] = "\
    -DPLUGIN_BLUETOOTHAUDIO=ON \
    -DPLUGIN_BLUETOOTHAUDIO_AUTOSTART="${WPE_BLUETOOTHAUDIO_AUTOSTART}" \
    ,-DPLUGIN_BLUETOOTHAUDIO=OFF \
    ,sbc \
"

PACKAGECONFIG[bluetoothaudio_sink] = "\
    -DPLUGIN_BLUETOOTHAUDIO_SINK_LATENCY="${WPE_BLUETOOTHAUDIO_SINK_LATENCY}" \
    ,, \
"
PACKAGECONFIG[bluetoothaudio_sink_codecsbc] = "\
    -DPLUGIN_BLUETOOTHAUDIO_SINK_CODECSBC_PRESET="${WPE_BLUETOOTHAUDIO_SINK_CODECSBC_PRESET}" \
    ,, \
"
PACKAGECONFIG[bluetoothaudio_source_codecsbc] = "\
    -DPLUGIN_BLUETOOTHAUDIO_SOURCE_CODECSBC_MAXBITPOOL="${WPE_BLUETOOTHAUDIO_SOURCE_CODECSBC_MAXBITPOOL}" \
    ,, \
"

PACKAGECONFIG[bluetoothsdpserver] = "\
    -DPLUGIN_BLUETOOTHSDPSERVER=ON \
    -DPLUGIN_BLUETOOTHSDPSERVER_CONTROLLER="${WPE_BLUETOOTH_CONTROLLER}" \
    -DPLUGIN_BLUETOOTHSDPSERVER_PROVIDER="${WPE_BLUETOOTHSDPSERVER_PROVIDER}" \
    -DPLUGIN_BLUETOOTHSDPSERVER_AUTOSTART="${WPE_BLUETOOTHSDPSERVER_AUTOSTART}" \
    ,-DPLUGIN_BLUETOOTHAUDIOSINK=OFF, \
"
PACKAGECONFIG[bluetoothaudiosink_sdpservice] = "\
    -DPLUGIN_BLUETOOTHSDPSERVER_SERVICE_A2DP_AUDIO_SOURCE="${WPE_BLUETOOTHSDPSERVER_SERVICE_A2DP_AUDIO}" \
    -DPLUGIN_BLUETOOTHSDPSERVER_SERVICE_A2DPAUDIOSOURCE_NAME="${WPE_BLUETOOTHSDPSERVER_SERVICE_A2DPAUDIOSINK_NAME}" \
    -DPLUGIN_BLUETOOTHSDPSERVER_SERVICE_A2DPAUDIOSOURCE_DESCRIPTION="${WPE_BLUETOOTHSDPSERVER_SERVICE_A2DPAUDIOSINK_DESCRIPTION}" \
    ,, \
 "
PACKAGECONFIG[bluetoothaudiosource_sdpservice] = "\
    -DPLUGIN_BLUETOOTHSDPSERVER_SERVICE_A2DP_AUDIO_SINK="${WPE_BLUETOOTHSDPSERVER_SERVICE_A2DP_AUDIO}" \
    -DPLUGIN_BLUETOOTHSDPSERVER_SERVICE_A2DPAUDIOSINK_NAME="${WPE_BLUETOOTHSDPSERVER_SERVICE_A2DPAUDIOSOURCE_NAME}" \
    -DPLUGIN_BLUETOOTHSDPSERVER_SERVICE_A2DPAUDIOSINK_DESCRIPTION="${WPE_BLUETOOTHSDPSERVER_SERVICE_A2DPAUDIOSOURCE_DESCRIPTION}" \
    ,, \
 "


