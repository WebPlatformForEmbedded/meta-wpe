# To support WPA-PSK(TKIP), 'no-rc4' has to be removed from the openssl

EXTRA_OECONF_remove_class-target = "no-rc4"
