# extend the wayland ref image

IMAGE_FEATURES += " \
    ssh-server-openssh \
"

# selected based on browser type override
# browser must be select through env variable: export BROWSER=qt and whitelisted with: export BB_ENV_EXTRAWHITE="$BB_ENV_EXTRAWHITE BROWSER"
OVERRIDES = "${BROWSER}"
IMAGE_INSTALL_append = "\
   packagegroup-ml-middleware \
   packagegroup-ml-wpe \
"
IMAGE_INSTALL_append_qt = "\
   packagegroup-ml-qt5browser \
"

