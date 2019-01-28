# Remote control provides remote control (RF4CE, IR or devinput) interfaces

REMOTECONTROL_RF4CE_NAME ?= "GreenPeak"
REMOTECONTROL_RF4CE_REMOTE_ID ?= "GPSTB"
REMOTECONTROL_RF4CE_MODULE ?= "/lib/modules/gpK5.ko"
REMOTECONTROL_RF4CE_NODE_ID ?= "249"
REMOTECONTROL_RF4CE_DEPENDENCY ?= "rf4ce-hal-gp"
REMOTECONTROL_POSTLOOKUP_CALLSIGN ?= ""
REMOTECONTROL_POSTLOOKUP_MAPFILE ?= ""

# ----------------------------------------------------------------------------

PACKAGECONFIG[remote]           = "-DPLUGIN_REMOTECONTROL=ON \
                                   -DPLUGIN_REMOTECONTROL_POSTLOOKUP_CALLSIGN=${REMOTECONTROL_POSTLOOKUP_CALLSIGN} \
                                   -DPLUGIN_REMOTECONTROL_POSTLOOKUP_MAPFILE=${REMOTECONTROL_POSTLOOKUP_MAPFILE} \
                                  ,-DPLUGIN_REMOTECONTROL=OFF,"
PACKAGECONFIG[remote-ir]        = "-DPLUGIN_REMOTECONTROL_IR=ON \
                                  ,-DPLUGIN_REMOTECONTROL_IR=OFF,"
PACKAGECONFIG[remote-devinput]  = "-DPLUGIN_REMOTECONTROL_DEVINPUT=ON,-DPLUGIN_REMOTECONTROL_DEVINPUT=OFF,"
PACKAGECONFIG[remote-gp]        = "-DPLUGIN_REMOTECONTROL_RFCE=ON \
                                   -DPLUGIN_REMOTECONTROL_RFCE_NAME=${REMOTECONTROL_RF4CE_NAME} \
                                   -DPLUGIN_REMOTECONTROL_RFCE_REMOTE_ID=${REMOTECONTROL_RF4CE_REMOTE_ID} \
                                   -DPLUGIN_REMOTECONTROL_RFCE_MODULE=${REMOTECONTROL_RF4CE_MODULE} \
                                   -DPLUGIN_REMOTECONTROL_RFCE_NODE_ID=${REMOTECONTROL_RF4CE_NODE_ID} \
                                   ,-DPLUGIN_REMOTECONTROL_RFCE=OFF,${REMOTECONTROL_RF4CE_DEPENDENCY}"


# ----------------------------------------------------------------------------

do_install_append() {
    if ${@bb.utils.contains("PACKAGECONFIG", "remote-devinput", "true", "false", d)}
    then
      cp -av --no-preserve=ownership ${WORKDIR}/osmc-devinput-remote.json ${D}${datadir}/WPEFramework/RemoteControl/devinput-remote.json
    fi
}

# ----------------------------------------------------------------------------

FILES_${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'remote-devinput', '${datadir}/WPEFramework/RemoteControl/devinput-remote.json', '', d)}"