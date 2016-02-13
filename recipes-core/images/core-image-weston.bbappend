# extend the wayland ref image

IMAGE_FEATURES += " \
    package-management \
    ssh-server-openssh \
"

IMAGE_INSTALL_append = "\
   packagegroup-ml-middleware \
   packagegroup-ml-wpe \
   ${@bb.utils.contains('BROWSER', 'qt', 'packagegroup-ml-qt5browser', '', d)} \
"
IMAGE_INSTALL_append_libc-glibc = " netflix "

