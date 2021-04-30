# Provides configuration for the FileTransfer Plugin
WPE_FILETRANSFER_AUTOSTART ??= "false"

# -----------------------------------------------------------------------------------------------------------
PACKAGECONFIG[filetransfer] = "\
    -DPLUGIN_FILETRANSFER=ON \
    -DPLUGIN_FILETRANSFER_AUTOSTART="${WPE_FILETRANSFER_AUTOSTART}" \
    ,-DPLUGIN_FILETRANSFER=OFF, \
"
# -----------------------------------------------------------------------------------------------------------
