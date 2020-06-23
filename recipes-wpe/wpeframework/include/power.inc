# WPE Power plugin is able to interface with the embedded system's power capabilities, such as AON blocks and suspend to ram

WPE_POWER_AUTOSTART ?= "true"
WPE_POWER_GPIOPIN ?= ""
WPE_POWER_GPIOTYPE ?= ""
WPE_POWER_PERSIST_STATE ?= "OFF"
WPE_POWER_DEP = "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/egl', 'broadcom-refsw', 'broadcom-refsw', '', d)}"
WPE_POWER_PLATFORM_IMPL = "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/egl', 'broadcom-refsw', 'Nexus', 'Stub', d)}"

# ----------------------------------------------------------------------------

PACKAGECONFIG[power] = "-DPLUGIN_POWER=ON -DPLUGIN_POWER_AUTOSTART=${WPE_POWER_AUTOSTART} \
    					-DPLUGIN_POWER_GPIOPIN=${WPE_POWER_GPIOPIN} \
    					-DPLUGIN_POWER_GPIOTYPE=${WPE_POWER_GPIOTYPE} \
                        -DPLUGIN_POWER_MFRPERSIST_STATE=${WPE_POWER_PERSIST_STATE} \
 					-DWPE_POWER_PLATFORM_IMPL=${WPE_POWER_PLATFORM_IMPL} \
    					,-DPLUGIN_POWER=OFF,${WPE_POWER_DEP}"

# ----------------------------------------------------------------------------
