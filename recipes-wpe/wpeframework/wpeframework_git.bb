SUMMARY = "Web Platform for Embedded Framework"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"
PR = "r0"

require wpeframework.inc

DEPENDS = "zlib"
DEPENDS_append_libc-musl = " libexecinfo"

PV = "3.0+git${SRCPV}"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEFramework.git;protocol=git;branch=master \
           file://wpeframework-init \
           file://wpeframework.service.in \
           file://0001-Thread.cpp-Include-limits.h-for-PTHREAD_STACK_MIN-de.patch \
"
SRCREV = "e7756de2c1d49db55b87322001b21a18a54851c4"

inherit cmake pkgconfig systemd update-rc.d

# Yocto root is under /home/root
WPEFRAMEWORK_PERSISTENT_PATH = "/home/root"
WPEFRAMEWORK_SYSTEM_PREFIX = "OE"

PACKAGECONFIG ?= "virtualinput"

PACKAGECONFIG[cyclicinspector]  = "-DWPEFRAMEWORK_TEST_CYCLICINSPECTOR=ON,-DWPEFRAMEWORK_TEST_CYCLICINSPECTOR=OFF,"
PACKAGECONFIG[opencdm]          = "-DWPEFRAMEWORK_CDMI=ON,-DWPEFRAMEWORK_CDMI=OFF,"
PACKAGECONFIG[provisionproxy]   = "-DWPEFRAMEWORK_PROVISIONPROXY=ON,-DWPEFRAMEWORK_PROVISIONPROXY=OFF,libprovision"
PACKAGECONFIG[testloader]       = "-DWPEFRAMEWORK_TEST_LOADER=ON,-DWPEFRAMEWORK_TEST_LOADER=OFF,"
PACKAGECONFIG[virtualinput]     = "-DWPEFRAMEWORK_VIRTUALINPUT=ON,-DWPEFRAMEWORK_VIRTUALINPUT=OFF,"

# FIXME, determine this a little smarter
# Provision event is required for libprovision and provision plugin
# Network is provided by the Network control plugin
# Location event is required for locationsync plugin
# Time event is required for timesync plugin
# Identifier event is required for Compositor plugin
# Internet event is provided by the LocationSync plugin

# Only enable certain events if wpeframework is in distro features
WPEFRAMEWORK_DIST_EVENTS ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wpeframework', 'Network', '', d)}"

WPEFRAMEWORK_EXTERN_EVENTS ?= "Location Time Internet \
    ${@bb.utils.contains('PACKAGECONFIG', 'opencdm', 'Decryption', '', d)} \
    ${WPEFRAMEWORK_DIST_EVENTS} \
    ${@bb.utils.contains('PACKAGECONFIG', 'provisionproxy', 'Provisioning', '', d)} \
"

EXTRA_OECMAKE += " \
    -DINSTALL_HEADERS_TO_TARGET=ON \
    -DEXTERN_EVENTS="${WPEFRAMEWORK_EXTERN_EVENTS}" \
    -DBUILD_SHARED_LIBS=ON \
    -DWPEFRAMEWORK_RPC=ON \
    -DBUILD_REFERENCE=${SRCREV} \
    -DTREE_REFERENCE=${SRCREV} \
    -DWPEFRAMEWORK_PERSISTENT_PATH=${WPEFRAMEWORK_PERSISTENT_PATH} \
    -DWPEFRAMEWORK_SYSTEM_PREFIX=${WPEFRAMEWORK_SYSTEM_PREFIX} \
"

do_install_append() {
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
        install -m 0755 ${WORKDIR}/wpeframework-init ${D}${sysconfdir}/init.d/wpeframework
    fi

    if ${@bb.utils.contains("PACKAGECONFIG", "opencdm", "true", "false", d)}
    then
        #install -d ${STAGING_INCDIR}
        install -m 0644 ${D}${includedir}/WPEFramework/interfaces/IDRM.h ${D}${includedir}/cdmi.h
    fi
}

SYSTEMD_SERVICE_${PN} = "wpeframework.service"

# ----------------------------------------------------------------------------

PACKAGES =+ "${PN}-initscript"

FILES_${PN}-initscript = "${sysconfdir}/init.d/wpeframework"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so ${datadir}/WPEFramework/* ${PKG_CONFIG_DIR}/*.pc"
FILES_${PN} += "${includedir}/cdmi.h"

# ----------------------------------------------------------------------------

INITSCRIPT_PACKAGES = "${PN}-initscript"
INITSCRIPT_NAME_${PN}-initscript = "wpeframework"

# If WPE Framework is enabled as distro feature, start earlier. Assuming packagegroup-wpe-boot is used and we're in control for the network
WPEFRAMEWORK_START = "${@bb.utils.contains('DISTRO_FEATURES', 'wpeframework', '40', '80', d)}"

INITSCRIPT_PARAMS_${PN}-initscript = "defaults ${WPEFRAMEWORK_START} 24"

RRECOMMENDS_${PN} = "${PN}-initscript"

# ----------------------------------------------------------------------------

INSANE_SKIP_${PN} += "dev-so"
INSANE_SKIP_${PN}-dbg += "dev-so"
