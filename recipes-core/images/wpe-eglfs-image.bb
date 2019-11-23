DESCRIPTION = "WPE Framework image for WPE fullscreen EGL without Wayland"
LICENSE = "MIT"

include recipes-core/images/wpe-image.bb

inherit features_check

CONFLICT_DISTRO_FEATURES = "wayland x11"

REQUIRED_DISTRO_FEATURES = "opengl"
