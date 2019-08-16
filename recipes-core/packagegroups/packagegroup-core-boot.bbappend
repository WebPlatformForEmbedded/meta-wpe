# remove network init scripts if we're doing a sysinitv build
# network is taken care of by thunder
RDEPENDS_${PN}_remove = "init-ifupdown"