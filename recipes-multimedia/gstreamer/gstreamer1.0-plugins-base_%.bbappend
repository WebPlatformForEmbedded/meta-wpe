PACKAGECONFIG[gl] = "-Dgl=enabled,,"
PACKAGECONFIG_append = " ${@oe.utils.ifelse(d.getVar('DISTRO_CODENAME', True) == 'dunfell', 'gl', '')}"
PACKAGECONFIG_append = " opus"

