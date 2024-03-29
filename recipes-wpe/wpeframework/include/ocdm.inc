# Provides configurations for OpenCDM Plugin and components
WPE_OPENCDMI_AUTOSTART ??= "true"
WPE_OPENCDMI_OUTOFPROCESS ??= "true"
WPE_OPENCDMI_USER ??= "${WPEFRAMEWORK_OCDM_GROUP}"

require ${@oe.utils.conditional('WPEFRAMEWORK_OCDM_GROUP', '', '', 'usergroup/ocdm.inc', d)}

WPE_OPENCDMI_USERGROUP_CONFIGS = "\
    ${@oe.utils.conditional('WPE_OPENCDMI_USER', '', '', '-DPLUGIN_OPENCDMI_USER=${WPE_OPENCDMI_USER}', d)} \
    ${@oe.utils.conditional('WPEFRAMEWORK_OCDM_GROUP', '', '', '-DPLUGIN_OPENCDMI_GROUP=${WPEFRAMEWORK_OCDM_GROUP}', d)} \
    ${@oe.utils.conditional('WPEFRAMEWORK_OCDM_GROUP', '', '', '-DPLUGIN_OPENCDMI_CONNECTOR="/tmp/ocdm|0770,${WPEFRAMEWORK_OCDM_GROUP}"', d)} \
"

PACKAGECONFIG[opencdmi] = "\
    -DPLUGIN_OPENCDMI=ON \
    -DPLUGIN_OPENCDMI_AUTOSTART="${WPE_OPENCDMI_AUTOSTART}" \
    -DPLUGIN_OPENCDMI_OUTOFPROCESS="${WPE_OPENCDMI_OUTOFPROCESS}" \
    ${WPE_OPENCDMI_USERGROUP_CONFIGS} \
    ,-DPLUGIN_OPENCDMI=OFF, \
"

PACKAGECONFIG[opencdmi_ck] = "-DPLUGIN_OPENCDMI_CLEARKEY=ON,,,wpeframework-ocdm-clearkey"
PACKAGECONFIG[opencdmi_ncas] = "-DPLUGIN_OPENCDMI_NCAS=ON,,,wpeframework-ocdm-ncas"
PACKAGECONFIG[opencdmi_pr] = "-DPLUGIN_OPENCDMI_PLAYREADY=ON,,,wpeframework-ocdm-playready"
PACKAGECONFIG[opencdmi_prnx] = "-DPLUGIN_OPENCDMI_PLAYREADY_NEXUS=ON,,,wpeframework-ocdm-playready-nexus"
PACKAGECONFIG[opencdmi_prnx_svp] = "-DPLUGIN_OPENCDMI_PLAYREADY_NEXUS_SVP=ON,,,wpeframework-ocdm-playready-nexus-svp"
# TODO: PACKAGECONFIG[opencdmi_nagra] = "-DPLUGIN_OPENCDMI_NAGRA=ON,,,wpeframework-ocdm-nagra"
PACKAGECONFIG[opencdmi_vgrdm] = "-DPLUGIN_OPENCDMI_PLAYREADY_VGDRM=ON,,,wpeframework-ocdm-vgrdm"
PACKAGECONFIG[opencdmi_wv] = "-DPLUGIN_OPENCDMI_WIDEVINE=ON,,,wpeframework-ocdm-widevine"
PACKAGECONFIG[opencdmi_wvnx_svp] = "-DOPENCDMI_WIDEVINE_NEXUS_SVP=ON,,,wpeframework-ocdm-widevine-nexus-svp"

