LICENSE = "CLOSED"

DEPENDS += "wpewebkit glib-2.0"

SRCREV = "b009236d5b7b2c0d1c9418cbb5c27141dda801b6"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEBackend-rdk.git;protocol=http;branch=master"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

# Default back end selections. Please override in your machine config using WPE_BACKEND=<> to meet your machine required
WPE_BACKEND ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'westeros', 'rpi', d)}"
WPE_BACKEND_append = "${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", " wayland","", d)}"
WPE_BACKEND_remove = "${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "westeros","", d)}"

WPE_BACKEND_x86 = "intelce"
WPE_BACKEND ?= "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/egl', 'broadcom-refsw', 'nexus', '', d)}"
WPE_BACKEND ?= "rpi"

PACKAGECONFIG ?= "${WPE_BACKEND}"

# device specific backends
PACKAGECONFIG[intelce]          = "-DUSE_BACKEND_INTEL_CE=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=ON,,intelce-display"
PACKAGECONFIG[nexus]            = "-DUSE_BACKEND_BCM_NEXUS=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=ON,,broadcom-refsw"
PACKAGECONFIG[rpi]              = "-DUSE_BACKEND_BCM_RPI=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=ON,,userland"

# Wayland selectors
PACKAGECONFIG[wayland]          = "-DUSE_BACKEND_WAYLAND=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF,,wayland libxkbcommon"
PACKAGECONFIG[westeros]         = "-DUSE_BACKEND_WESTEROS=ON -DUSE_BACKEND_BCM_RPI=OFF -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF,,wayland westeros libxkbcommon"
PACKAGECONFIG[bcm-weston]       = "-DUSE_BACKEND_BCM_NEXUS_WAYLAND=ON,-DUSE_BACKEND_BCM_NEXUS_WAYLAND=OFF,,"
PACKAGECONFIG[wpeframework]     = "-DUSE_BACKEND_WPEFRAMEWORK=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF -DUSE_INPUT_LIBINPUT=OFF,,wayland westeros libxkbcommon wpeframework wpeframework-plugins"

# MESA
PACKAGECONFIG[westeros-mesa]    = "-DUSE_BACKEND_WESTEROS_MESA=ON,,"

# other switches
PACKAGECONFIG[debug]            = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"
PACKAGECONFIG[libinput]         = "-DUSE_INPUT_LIBINPUT=ON,-DUSE_INPUT_LIBINPUT=OFF,libinput,"
PACKAGECONFIG[libinput-udev]    = "-DUSE_INPUT_UDEV=ON,-DUSE_INPUT_UDEV=OFF,libinput,"
PACKAGECONFIG[virtualinput]     = "-DUSE_VIRTUAL_KEYBOARD=ON,-DUSE_VIRTUAL_KEYBOARD=OFF,wpeframework,"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/libWPEBackend-default.so ${libdir}/libWPEBackend-rdk.so"
INSANE_SKIP_${PN} = "dev-so textrel"
