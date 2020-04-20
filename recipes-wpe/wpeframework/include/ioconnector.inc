# WPE IOConnector plugin is able to control GPIO pin states

WPE_IOCONNECTOR_AUTOSTART ?= "false"
WPE_IOCONNECTOR_PAIRING_PIN ?= ""
WPE_IOCONNECTOR_PAIRING_CALLSIGN ?= ""
WPE_IOCONNECTOR_PAIRING_PRODUCER ?= ""

# ----------------------------------------------------------------------------

PACKAGECONFIG[ioconnector] = "-DPLUGIN_IOCONNECTOR=ON -DPLUGIN_IOCONNECTOR_AUTOSTART=${WPE_IOCONNECTOR_AUTOSTART} \
    					-DPLUGIN_IOCONNECTOR_PAIRING_PIN=${WPE_IOCONNECTOR_PAIRING_PIN} \
    					-DPLUGIN_IOCONNECTOR_PAIRING_CALLSIGN=${WPE_IOCONNECTOR_PAIRING_CALLSIGN} \
                                        -DPLUGIN_IOCONNECTOR_PAIRING_PRODUCER=${WPE_IOCONNECTOR_PAIRING_PRODUCER} \
    					,-DPLUGIN_IOCONNECTOR=OFF,"

# ----------------------------------------------------------------------------
