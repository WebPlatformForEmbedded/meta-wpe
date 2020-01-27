# Provider for RPI platform has to be updated once the implementation for mfr fwupgrade is available
RDEPENDS_${PN} += "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/mfrlib', 'mfrlibs-rpi', 'mfrlibs-rpi', '', d)}"
RDEPENDS_${PN} += "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/mfrfwlib', 'fwupgrade-lib', 'fwupgrade-lib', '', d)}"

# Additional flags
WPE_FIRMWARECONTROL_AUTOSTART ?= "true"
# ----------------------------------------------------------------------------
WPE_FIRMWARECONTROL_EXTRAFLAGS += ' \
-DPLUGIN_FIRMWARECONTROL_AUTOSTART=${WPE_FIRMWARECONTROL_AUTOSTART} \
'

WPE_FIRMWARECONTROL_EXTRAFLAGS += "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/mfrlib', 'mfrlibs-rpi', '-DPLUGIN_FIRMWARECONTROL_PLATFORM_RPI=ON', '', d)}"
# ----------------------------------------------------------------------------

PACKAGECONFIG[firmwarecontrol]  = " \
    -DPLUGIN_FIRMWARECONTROL=ON \
    ${WPE_FIRMWARECONTROL_EXTRAFLAGS} \
    ,-DPLUGIN_FIRMWARECONTROL=OFF,"

# ----------------------------------------------------------------------------

