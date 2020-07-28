# WPE IOConnector plugin is able to control GPIO pin states

WPE_IOCONNECTOR_AUTOSTART ?= "true"
WPE_IOCONNECTOR_PAIRING_PIN ?= ""
WPE_IOCONNECTOR_PAIRING_CALLSIGN ?= ""
WPE_IOCONNECTOR_PAIRING_PRODUCER ?= ""

# Set details of ioconnector pins "Id:Mode:ActiveLow".
# Example "169:output:true;172:output:false;"
WPE_IOCONNECTOR_PINS ?= "::;::;::;"

# ----------------------------------------------------------------------------

PACKAGECONFIG[ioconnector] = "-DPLUGIN_IOCONNECTOR=ON -DPLUGIN_IOCONNECTOR_AUTOSTART=${WPE_IOCONNECTOR_AUTOSTART} \
    					-DPLUGIN_IOCONNECTOR_PAIRING_PIN=${WPE_IOCONNECTOR_PAIRING_PIN} \
    					-DPLUGIN_IOCONNECTOR_PAIRING_CALLSIGN=${WPE_IOCONNECTOR_PAIRING_CALLSIGN} \
                                        -DPLUGIN_IOCONNECTOR_PAIRING_PRODUCER=${WPE_IOCONNECTOR_PAIRING_PRODUCER} \
                                        -DPLUGIN_IOCONNECTOR_PINS='${WPE_IOCONNECTOR_PINS}' \
    					,-DPLUGIN_IOCONNECTOR=OFF,"
# ----------------------------------------------------------------------------
