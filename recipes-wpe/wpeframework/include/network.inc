# Configuration option for WPE NetworkControl plugin

WPE_NETWORKCONTROL_AUTOSTART ??= "true"
WPE_NETWORKCONTROL_DHCP_RETRIES ??= "4"
WPE_NETWORKCONTROL_DHCP_RESONSE_TIMEOUT ??= "5"

# Set details of network interfaces in the format "Name:Mode:Address:Netmask:Gateway".
# Example "wlan0:Manual:192.168.55.1:24:192.168.55.1;eth0:Manual:192.168.1.158:24:192.168.1.1;eth1:Dynamic:::;"
WPE_NETWORKCONTROL_INTERFACES ??= "wlan0:Dynamic:::;eth0:Dynamic:::;"

# ---------------------------------------------------------------------------------------------------------------------
PACKAGECONFIG[network] = "-DPLUGIN_NETWORKCONTROL=ON \
    -DPLUGIN_NETWORKCONTROL_AUTOSTART=${WPE_NETWORKCONTROL_AUTOSTART} \
    -DPLUGIN_NETWORKCONTROL_DHCP_RETRIES=${WPE_NETWORKCONTROL_DHCP_RETRIES} \
    -DPLUGIN_NETWORKCONTROL_DHCP_RESONSE_TIMEOUT=${WPE_NETWORKCONTROL_DHCP_RESONSE_TIMEOUT} \
    -DPLUGIN_NETWORKCONTROL_INTERFACES='${WPE_NETWORKCONTROL_INTERFACES}' \
    ,-DPLUGIN_NETWORKCONTROL=OFF,"
# ---------------------------------------------------------------------------------------------------------------------
