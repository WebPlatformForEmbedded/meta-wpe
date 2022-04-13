# This deprecated config file is added to keep configs which are required backward compatibility

PACKAGECONFIG:append = "\
    ${@bb.utils.contains('MACHINE_FEATURES', 'bluetooth', 'bluetooth', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_debug', 'deprecatedbuildtype_thunder_debug', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_debugoptimized', 'deprecatedbuildtype_thunder_debugoptimized', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_releasesymbols', 'deprecatedbuildtype_thunder_releasesymbols', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_release', 'deprecatedbuildtype_thunder_release', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'thunder_production', 'deprecatedbuildtype_thunder_production', '', d)} \
"

PACKAGECONFIG[deprecatedbuildtype_thunder_debug] = "-DBUILD_TYPE=Debug,,"
PACKAGECONFIG[deprecatedbuildtype_thunder_debugoptimized] = "-DBUILD_TYPE=DebugOptimized,,"
PACKAGECONFIG[deprecatedbuildtype_thunder_releasesymbols] = "-DBUILD_TYPE=ReleaseSymbols,,"
PACKAGECONFIG[deprecatedbuildtype_thunder_release] = "-DBUILD_TYPE=Release,,"
PACKAGECONFIG[deprecatedbuildtype_thunder_production] = "-DBUILD_TYPE=Production,,"

PACKAGECONFIG[bluetooth] = "-DBLUETOOTH=ON -DBLUETOOTH_SUPPORT=ON, -DBLUETOOTH=OFF,bluez5"
PACKAGECONFIG[broadcast] = "-DBROADCAST=ON,-DBROADCAST=OFF,"
PACKAGECONFIG[broadcastsiparse] = "-DBROADCAST_SI_PARSING=ON,-DBROADCAST_SI_PARSING=OFF,"

# Keeping the init scripts from build system for backward compatibility
USE_INIT_SCRIPT_FROM_BUILDSYSTEM ??= "${@bb.utils.contains('PACKAGECONFIG', 'initscriptsupport', 'n', 'y', d)}"
INIT_SCRIPT_FILE ??= "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'file://wpeframework.service.in', 'file://wpeframework-init', d)}"

SRC_URI:append = " ${@bb.utils.contains('USE_INIT_SCRIPT_FROM_BUILDSYSTEM', 'y', '${INIT_SCRIPT_FILE}', '', d)}"

do_install:append() {
    if ${@bb.utils.contains("USE_INIT_SCRIPT_FROM_BUILDSYSTEM", "y", "true", "false", d)}
    then
        if ${@bb.utils.contains("DISTRO_FEATURES", "systemd", "true", "false", d)}
        then
            if ${@bb.utils.contains("MACHINE_FEATURES", "platformserver", "true", "false", d)}
            then
               extra_after=""
            elif ${@bb.utils.contains("PREFERRED_PROVIDER_virtual/egl", "broadcom-refsw", "true", "false", d)}
            then
               extra_after="nxserver.service"
            fi
            extra_after="${extra_after} ${WAYLAND_COMPOSITOR}"
            install -d ${D}${systemd_unitdir}/system
            sed -e "s|@EXTRA_AFTER@|${extra_after}|g" < ${WORKDIR}/wpeframework.service.in > ${D}${systemd_unitdir}/system/wpeframework.service
        else
            install -d ${D}${sysconfdir}/init.d
            sed -e "s|WPEFRAMEWORK_PERSISTENT_PATH|${WPEFRAMEWORK_PERSISTENT_PATH}|g" < ${WORKDIR}/wpeframework-init > ${D}${sysconfdir}/init.d/wpeframework
            chmod +x ${D}${sysconfdir}/init.d/wpeframework
        fi
    fi
}
