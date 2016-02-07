# extend the wayland ref image

IMAGE_FEATURES += " \
    ssh-server-openssh \
"

IMAGE_INSTALL_append = "\
   packagegroup-ml-middleware \
   packagegroup-ml-wpe \
"
IMAGE_INSTALL_append_qt = "\
   packagegroup-ml-qt5browser \
"

