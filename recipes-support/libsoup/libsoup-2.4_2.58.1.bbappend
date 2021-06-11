SRC_URI_remove = "${@oe.utils.ifelse(d.getVar('DISTRO_CODENAME', True) == 'dunfell', 'file://0001-soup-cookie-jar-add-API-to-set-a-limit-of-cookies-in.patch', '')}"
