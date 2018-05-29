DESCRIPTION = "WPE Framework image for WPE fullscreen EGL without Wayland"
LICENSE = "MIT"

include recipes-core/images/wpe-image.bb

inherit distro_features_check

DISTRO_FEATURES_remove = "wayland"
