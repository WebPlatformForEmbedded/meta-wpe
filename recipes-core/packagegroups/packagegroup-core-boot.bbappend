# remove network init scripts if we're doing a sysinitv build
# network is taken care of by thunder
RDEPENDS_${PN}_remove = "${@bb.utils.contains('DISTRO_FEATURES', 'thunder', 'init-ifupdown', '', d)}"