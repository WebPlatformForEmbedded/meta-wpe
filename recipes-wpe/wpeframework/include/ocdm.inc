# OpenCDM related switches

PACKAGECONFIG += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm',              'opencdmi', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'clearkey',             'opencdmi_ck', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready',            'opencdmi_pr', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_nexus',      'opencdmi_prnx', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_nexus_svp',  'opencdmi_prnx_svp', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'playready_vg',         'opencdmi_vgrdm', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'widevine',             'opencdmi_wv', '', d)} \
"

# ----------------------------------------------------------------------------

PACKAGECONFIG[opencdmi]       = "-DPLUGIN_OPENCDMI=ON \
                                 -DPLUGIN_OPENCDMI_AUTOSTART=true \
                                 -DPLUGIN_OPENCDMI_OOP=true \
                                ,,"
PACKAGECONFIG[opencdmi_ck]    = "-DPLUGIN_OPENCDMI_CLEARKEY=ON,,,wpeframework-ocdm-clearkey"
PACKAGECONFIG[opencdmi_pr]    = "-DPLUGIN_OPENCDMI_PLAYREADY=ON,,,wpeframework-ocdm-playready"
PACKAGECONFIG[opencdmi_prnx]  = "-DPLUGIN_OPENCDMI_PLAYREADY_NEXUS=ON,,,wpeframework-ocdm-playready-nexus"
PACKAGECONFIG[opencdmi_prnx_svp]  = "-DPLUGIN_OPENCDMI_PLAYREADY_NEXUS_SVP=ON,,,wpeframework-ocdm-playready-nexus-svp"
PACKAGECONFIG[opencdmi_vgrdm] = "-DPLUGIN_OPENCDMI_PLAYREADY_VGDRM=ON,,"
PACKAGECONFIG[opencdmi_wv]    = "-DPLUGIN_OPENCDMI_WIDEVINE=ON,,,wpeframework-ocdm-widevine"

# ----------------------------------------------------------------------------
