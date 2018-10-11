SUMMARY = "WPE WebKit RDK backend"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=ab5b52d145a58f5fcc0e2a531e7a2370"

DEPENDS += "libwpe glib-2.0"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEBackend-rdk.git;protocol=http;branch=master"
SRCREV = "ad954707dca691f850560980e6786c0206e96891"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

# Default back end selections. Please override in your machine config using WPE_BACKEND=<> to meet your machine required
WPE_BACKEND ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'westeros', 'rpi', d)}"
WPE_BACKEND_append = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', ' wayland','', d)}"
WPE_BACKEND_remove = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', 'westeros','', d)}"

WPE_BACKEND_x86 = "intelce"
WPE_BACKEND ?= "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/egl', 'broadcom-refsw', 'nexus', '', d)}"
WPE_BACKEND ?= "rpi"

PACKAGECONFIG ?= "${WPE_BACKEND}"

# device specific backends
PACKAGECONFIG[imx6]             = "-DUSE_BACKEND_VIV_IMX6_EGL=ON,,imx-gpu-viv"
PACKAGECONFIG[intelce]          = "-DUSE_BACKEND_INTEL_CE=ON,,intelce-display"
PACKAGECONFIG[nexus]            = "-DUSE_BACKEND_BCM_NEXUS=ON,,broadcom-refsw"
PACKAGECONFIG[rpi]              = "-DUSE_BACKEND_BCM_RPI=ON,,userland"

# Wayland selectors
PACKAGECONFIG[wayland]          = "-DUSE_BACKEND_WAYLAND=ON,,wayland libxkbcommon"
PACKAGECONFIG[wayland-egl]      = "-DUSE_BACKEND_WAYLAND_EGL=ON,,wayland libxkbcommon"
PACKAGECONFIG[westeros]         = "-DUSE_BACKEND_WESTEROS=ON,,wayland westeros libxkbcommon"
PACKAGECONFIG[bcm-weston]       = "-DUSE_BACKEND_BCM_NEXUS_WAYLAND=ON,,wayland wayland-egl-bnxs libxkbcommon"
PACKAGECONFIG[wpeframework]     = "-DUSE_BACKEND_WPEFRAMEWORK=ON,,wayland westeros wpeframework wpeframework-plugins libxkbcommon xkeyboard-config"

# MESA
PACKAGECONFIG[westeros-mesa]    = "-DUSE_BACKEND_WESTEROS_MESA=ON,,"

# other switches
PACKAGECONFIG[debug]            = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"
PACKAGECONFIG[libinput]         = "-DUSE_INPUT_LIBINPUT=ON,-DUSE_INPUT_LIBINPUT=OFF,libinput,"
PACKAGECONFIG[libinput-udev]    = "-DUSE_INPUT_UDEV=ON,-DUSE_INPUT_UDEV=OFF,libinput,"
PACKAGECONFIG[virtualinput]     = "-DUSE_VIRTUAL_KEYBOARD=ON,-DUSE_VIRTUAL_KEYBOARD=OFF,wpeframework,"

# FIXME RDEPENDS on PACKAGECONFIG is not behaving as it should
RDEPENDS_${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'wpeframework', 'xkeyboard-config', '', d)}"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/libWPEBackend-default.so ${libdir}/libWPEBackend-rdk.so"
INSANE_SKIP_${PN} = "dev-so textrel"
