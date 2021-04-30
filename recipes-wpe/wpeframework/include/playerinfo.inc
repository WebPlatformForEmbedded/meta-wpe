# Provides configurations for PlayerInfo Plugin
WPE_PLAYERINFO_DEP ??= ""
WPE_PLAYERINFO_AUTOSTART ??= "true"

WPE_PLAYERINFO_DOLBY_PLATFORM_IMPL ??= "None"

# -----------------------------------------------------------------------------------------------------------
PACKAGECONFIG[playerinfo] = "\
    -DPLUGIN_PLAYERINFO=ON \
    -DPLUGIN_PLAYERINFO_AUTOSTART=${WPE_PLAYERINFO_AUTOSTART} \
    ,-DPLUGIN_PLAYERINFO=OFF,${WPE_PLAYERINFO_DEP} \
"

PACKAGECONFIG[playerinfo_dolby] = "\
    -DDOLBY_SUPPORT=ON \
    -DDOLBY_IMPLEMENTATION=${WPE_PLAYERINFO_DOLBY_PLATFORM_IMPL} \
    ,-DDOLBY_SUPPORT=OFF, \
"
# -----------------------------------------------------------------------------------------------------------
