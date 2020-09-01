# OpenCDM related switches

PACKAGECONFIG[opencdmi]       = "-DENABLE_OPEN_CDMI=ON \
                                 -DPLUGIN_OPENCDMI_AUTOSTART=true \
                                 -DPLUGIN_OPENCDMI_OOP=true \
                                ,,"
PACKAGECONFIG[opencdmi_ck]    = "-DPLUGIN_OPENCDMI_CLEARKEY=ON,,,wpeframework-ocdm-clearkey"
PACKAGECONFIG[opencdmi_ncas]  = "-DPLUGIN_OPENCDMI_NCAS=ON,,,wpeframework-ocdm-ncas"
PACKAGECONFIG[opencdmi_pr]    = "-DPLUGIN_OPENCDMI_PLAYREADY=ON,,,wpeframework-ocdm-playready"
PACKAGECONFIG[opencdmi_prnx]  = "-DPLUGIN_OPENCDMI_PLAYREADY_NEXUS=ON,,,wpeframework-ocdm-playready-nexus"
PACKAGECONFIG[opencdmi_prnx_svp]  = "-DPLUGIN_OPENCDMI_PLAYREADY_NEXUS_SVP=ON,,,wpeframework-ocdm-playready-nexus-svp"
PACKAGECONFIG[opencdmi_vgrdm] = "-DPLUGIN_OPENCDMI_PLAYREADY_VGDRM=ON,,"
PACKAGECONFIG[opencdmi_wv]    = "-DPLUGIN_OPENCDMI_WIDEVINE=ON,,,wpeframework-ocdm-widevine"
