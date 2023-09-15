SUMMARY = "WPE Backend for RDK"
DESCRIPTION = "Backend for WPE with specific support for embedded devices used on the RDK"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded/WPEBackend-rdk"
BUGTRACKER = "https://github.com/WebPlatformForEmbedded/WPEBackend-rdk/issues"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://src/wayland/display.h;;beginline=5;endline=24;md5=b1c8cb6b0857048a21b33611f01c575a"

DEPENDS_append = " libwpe glib-2.0"
PROVIDES += "virtual/wpebackend"

RECIPE_BRANCH ?= "master"
PV = "git${SRCPV}"
SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEBackend-rdk.git;branch=${RECIPE_BRANCH};protocol=https"
SRCREV ?= "108e4ab0da043421202e3ef64e0a38d1db8b82ee"
S = "${WORKDIR}/git"

inherit cmake pkgconfig

# BACKEND has too many flavors to pick on through conventional methods
# By default we'll take wpeframework when compositor is enabled in DISTRO_FEATURES.
# However if that is not available take westeros when wayland is there, if all that is not take the EGLFS for SOC
# which in our case most commonly is RPI
def backendselector(d):
    wayland = bb.utils.contains('DISTRO_FEATURES', 'wayland', True, False, d)
    compositor = bb.utils.contains('DISTRO_FEATURES', 'compositor', True, False, d)

    if compositor == False and wayland == False:
        return 'platform'
    elif compositor == False and wayland == True:
        return 'westeros'
    else:
        return 'wpeframework'


# Default back end selections. Please override in your machine config using WPE_BACKEND=<> to meet your machine required
WPE_BACKEND_SELECTED = "${@backendselector(d)}"
WPE_BACKEND ??= " ${@bb.utils.contains('WPE_BACKEND_SELECTED', 'platform', '${WPE_BACKEND_PLATFORM}', '${WPE_BACKEND_SELECTED}', d)}"

PACKAGECONFIG ?= "${WPE_BACKEND} virtualinput"

# device specific backends
PACKAGECONFIG[intelce] = "-DUSE_BACKEND_INTEL_CE=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=ON,,intelce-display"
PACKAGECONFIG[nexus] = "-DUSE_BACKEND_BCM_NEXUS=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=ON,,broadcom-refsw"
PACKAGECONFIG[rpi] = "-DUSE_BACKEND_BCM_RPI=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=ON,,userland"
PACKAGECONFIG[stm] = "-DUSE_BACKEND_STM=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF,,libxkbcommon xkeyboard-config"
PACKAGECONFIG[imx6] = "-DUSE_BACKEND_VIV_IMX6_EGL=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=ON,,imx-gpu-viv"

# Wayland selectors
PACKAGECONFIG[wayland] = "-DUSE_BACKEND_WAYLAND_EGL=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF,,wayland libxkbcommon xkeyboard-config"
PACKAGECONFIG[realtek] = "-DUSE_BACKEND_REALTEK=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF,,wayland libxkbcommon xkeyboard-config"
PACKAGECONFIG[westeros] = "-DUSE_BACKEND_WESTEROS=ON -DUSE_BACKEND_BCM_RPI=OFF -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF -DUSE_WESTEROS_SINK=ON,,wayland westeros libxkbcommon xkeyboard-config"
PACKAGECONFIG[bcm-weston] = "-DUSE_BACKEND_BCM_NEXUS_WAYLAND=ON,-DUSE_BACKEND_BCM_NEXUS_WAYLAND=OFF,,"
PACKAGECONFIG[westeros-mesa] = "-DUSE_BACKEND_WESTEROS_MESA=ON,,"
PACKAGECONFIG[wpeframework] = "-DUSE_BACKEND_WPEFRAMEWORK=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF -DUSE_INPUT_LIBINPUT=OFF,,wpeframework-clientlibraries wpeframework-plugins libxkbcommon xkeyboard-config"

# MESA
PACKAGECONFIG[westeros-mesa] = "-DUSE_BACKEND_WESTEROS_MESA=ON,,"

# other switches
PACKAGECONFIG[debug] = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"
PACKAGECONFIG[libinput] = "-DUSE_INPUT_LIBINPUT=ON,-DUSE_INPUT_LIBINPUT=OFF,libinput,"
PACKAGECONFIG[libinput-udev] = "-DUSE_INPUT_UDEV=ON,-DUSE_INPUT_UDEV=OFF,libinput,"
PACKAGECONFIG[virtualinput] = "-DUSE_VIRTUAL_KEYBOARD=ON,-DUSE_VIRTUAL_KEYBOARD=OFF,wpeframework-clientlibraries,"

do_install() {
    install -d ${D}${libdir}
    install -m 0755 ${B}/libWPEBackend-*.so ${D}${libdir}/
}

FILES_SOLIBSDEV = ""

FILES_${PN} += "${libdir}/libWPEBackend-default.so ${libdir}/libWPEBackend-rdk.so"
INSANE_SKIP = "dev-so"

# FIXME RDEPENDS on PACKAGECONFIG is not behaving as it should
RDEPENDS_${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'wpeframework', 'xkeyboard-config', '', d)}"
RPROVIDES_${PN} += "virtual/wpebackend"
