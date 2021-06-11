PACKAGECONFIG[matroska] = "-Dmatroska=enabled,,"
PACKAGECONFIG_append = " ${@oe.utils.ifelse(d.getVar('DISTRO_CODENAME', True) == 'dunfell', 'matroska', '')}"
PACKAGECONFIG_append = " mpg123"

