# Network and Timesync are handled through Thunder/WPEFramework
PACKAGECONFIG_remove = "${@bb.utils.contains('DISTRO_FEATURES', 'thunder', 'networkd timedated timesyncd resolved', '', d)}"