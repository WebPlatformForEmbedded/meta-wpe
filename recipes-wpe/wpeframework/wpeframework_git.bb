SUMMARY = "Web Platform for Embedded Framework"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "zlib"
DEPENDS_append_libc-musl = " libexecinfo"

PV = "3.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/WPEFramework.git;protocol=ssh;branch=master \
           file://wpeframework-init \
           file://wpeframework.service.in \
           file://0001-Thread.cpp-Include-limits.h-for-PTHREAD_STACK_MIN-de.patch \
"
SRCREV = "e9d1a389462d091097faea81a17dff94c9ee1d7c"

S = "${WORKDIR}/git"

inherit cmake pkgconfig systemd

PACKAGECONFIG ?= "provisionproxy virtualinput"

PACKAGECONFIG[cyclicinspector]  = "-DWPEFRAMEWORK_TEST_CYCLICINSPECTOR=ON,-DWPEFRAMEWORK_TEST_CYCLICINSPECTOR=OFF,"
PACKAGECONFIG[debug]            = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"
PACKAGECONFIG[provisionproxy]   = "-DWPEFRAMEWORK_PROVISIONPROXY=ON,-DWPEFRAMEWORK_PROVISIONPROXY=OFF,libprovision"
PACKAGECONFIG[testloader]       = "-DWPEFRAMEWORK_TEST_LOADER=ON,-DWPEFRAMEWORK_TEST_LOADER=OFF,"
PACKAGECONFIG[virtualinput]     = "-DWPEFRAMEWORK_VIRTUALINPUT=ON,-DWPEFRAMEWORK_VIRTUALINPUT=OFF,"

EXTRA_OECMAKE += " \
    -DINSTALL_HEADERS_TO_TARGET=ON \
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
}

SYSTEMD_SERVICE_${PN} = "wpeframework.service"

# ----------------------------------------------------------------------------

PACKAGES =+ "${PN}-initscript"

FILES_${PN}-initscript = "${sysconfdir}/init.d/wpeframework"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so ${datadir}/WPEFramework/* ${PKG_CONFIG_DIR}/*.pc"

# ----------------------------------------------------------------------------

INITSCRIPT_PACKAGES = "${PN}-initscript"
INITSCRIPT_NAME_${PN}-initscript = "wpeframework"
INITSCRIPT_PARAMS_${PN}-initscript = "defaults 80 24"

RRECOMMENDS_${PN} = "${PN}-initscript"

# ----------------------------------------------------------------------------

INSANE_SKIP_${PN} += "dev-so"
INSANE_SKIP_${PN}-dbg += "dev-so"

TOOLCHAIN = "gcc"
