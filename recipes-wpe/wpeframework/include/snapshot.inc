# Snapshot only works on BRCM STBs and RPIs
WPE_SNAPSHOT ?= ""
WPE_SNAPSHOT_rpi = "snapshot"
WPE_SNAPSHOT_nexus = "snapshot"
WPE_SNAPSHOT_DEP = "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/egl', 'broadcom-refsw', 'broadcom-refsw', 'userland', d)}"

# ----------------------------------------------------------------------------

PACKAGECONFIG[snapshot]       = "-DPLUGIN_SNAPSHOT=ON,-DPLUGIN_SNAPSHOT=OFF,${WPE_SNAPSHOT_DEP} libpng"

# ----------------------------------------------------------------------------