SUMMARY = "WPE WebKit RDK backend"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=ab5b52d145a58f5fcc0e2a531e7a2370"

DEPENDS += "libwpe glib-2.0"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEBackend-rdk.git;protocol=http;branch=master"
SRCREV = "f43a16af156c2c9a1eb1a8fb3a5d03dfdc47c5b4"

S = "${WORKDIR}/git"

PROVIDES += "virtual/wpebackend"
RPROVIDES_${PN} += "virtual/wpebackend"

inherit cmake pkgconfig

# Default back end selections. Please override in your machine config using WPE_BACKEND=<> to meet your machine required
WPE_BACKEND ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'westeros', 'wpeframework', d)}"
WPE_BACKEND_append = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', ' wayland','', d)}"
WPE_BACKEND_remove = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', 'westeros','', d)}"

WPE_BACKEND_x86 = "intelce"
WPE_BACKEND ?= "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/egl', 'broadcom-refsw', 'nexus', '', d)}"
WPE_BACKEND ?= "rpi"

PACKAGECONFIG ?= "${WPE_BACKEND} virtualinput"

# device specific backends
PACKAGECONFIG[intelce]          = "-DUSE_BACKEND_INTEL_CE=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=ON,,intelce-display"
PACKAGECONFIG[nexus]            = "-DUSE_BACKEND_BCM_NEXUS=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=ON,,broadcom-refsw"
PACKAGECONFIG[rpi]              = "-DUSE_BACKEND_BCM_RPI=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=ON,,userland"

# Wayland selectors
PACKAGECONFIG[wayland]          = "-DUSE_BACKEND_WAYLAND=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF,,wayland libxkbcommon"
PACKAGECONFIG[westeros]         = "-DUSE_BACKEND_WESTEROS=ON -DUSE_BACKEND_BCM_RPI=OFF -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF,,wayland westeros libxkbcommon"
PACKAGECONFIG[bcm-weston]       = "-DUSE_BACKEND_BCM_NEXUS_WAYLAND=ON,-DUSE_BACKEND_BCM_NEXUS_WAYLAND=OFF,,"
PACKAGECONFIG[wpeframework]     = "-DUSE_BACKEND_WPEFRAMEWORK=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF -DUSE_INPUT_LIBINPUT=OFF,,wpeframework libxkbcommon xkeyboard-config"

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
