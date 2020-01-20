WPE_FIRMWARECONTROL_EXTRAFLAGS += "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/mfrlib', 'mfrlibs-rpi', '\
     -DPLUGIN_FIRMWARECONTROL_PLATFORM_RPI=ON \
', '', d)}"

WPE_FIRMWARECONTROL_EXTRAFLAGS += "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/mfrlib', 'broadcom-mfrlib', '\
     -DPLUGIN_FIRMWARECONTROL_PLATFORM_BCM=ON \
', '', d)}"

WPE_FIRMWARECONTROL_EXTRAFLAGS += "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/mfrlib', 'fwupgrade-lib', '\
     -DPLUGIN_FIRMWARECONTROL_PLATFORM_BCM=OFF \
', '', d)}"

RDEPENDS_${PN} += "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/mfrlib', 'mfrlibs-rpi', 'mfrlibs-rpi', '', d)}"
RDEPENDS_${PN} += "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/mfrlib', 'broadcom-mfrlib', 'broadcom-mfrlib', '', d)}"
RDEPENDS_${PN} += "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/mfrlib', 'fwupgrade-lib', 'fwupgrade-lib', '', d)}"

# Additional flags
WPE_FIRMWARECONTROL_AUTOSTART ?= "true"
# ----------------------------------------------------------------------------
WPE_FIRMWARECONTROL_EXTRAFLAGS += ' \
-DPLUGIN_FIRMWARECONTROL_AUTOSTART=${WPE_FIRMWARECONTROL_AUTOSTART} \
'
# ----------------------------------------------------------------------------

PACKAGECONFIG[firmwarecontrol]  = " \
    -DPLUGIN_FIRMWARECONTROL=ON \
    ${WPE_FIRMWARECONTROL_EXTRAFLAGS} \
    ,-DPLUGIN_FIRMWARECONTROL=OFF,"

# ----------------------------------------------------------------------------

