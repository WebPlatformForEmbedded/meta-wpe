# Provides configuration for the DHCPServer Plugin
WPE_DHCPSERVER_AUTOSTART ??= "false"
WPE_DHCPSERVER_OUTOFPROCESS ??= "true"

# -----------------------------------------------------------------------------------------------------------
PACKAGECONFIG[dhcpserver] = "\
    -DPLUGIN_DHCPSERVER=ON \
    -DPLUGIN_DHCPSERVER_AUTOSTART="${WPE_DHCPSERVER_AUTOSTART}" \
    -DPLUGIN_DHCPSERVER_OUTOFPROCESS="${WPE_DHCPSERVER_OUTOFPROCESS}" \
    ,-DPLUGIN_DHCPSERVER=OFF, \
"
# -----------------------------------------------------------------------------------------------------------