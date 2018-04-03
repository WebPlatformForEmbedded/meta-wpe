# Netflix does not like curl + gnutls, switch to curl + openssl
PACKAGECONFIG_remove = "gnutls"
PACKAGECONFIG_append = " ssl"
