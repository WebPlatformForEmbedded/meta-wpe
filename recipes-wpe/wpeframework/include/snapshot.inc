# Snapshot only works on BRCM STBs and RPIs
WPE_SNAPSHOT_DEP ?= ""
WPE_SNAPSHOT_DEP_brcm = "broadcom-refsw"

# ----------------------------------------------------------------------------

PACKAGECONFIG[snapshot]       = "-DPLUGIN_SNAPSHOT=ON,-DPLUGIN_SNAPSHOT=OFF,${WPE_SNAPSHOT_DEP} libpng"

# ----------------------------------------------------------------------------
