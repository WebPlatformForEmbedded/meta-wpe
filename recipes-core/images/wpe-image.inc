# selected based on browser type override
# browser must be select through env variable: export BROWSER=qt and whitelisted with: export BB_ENV_EXTRAWHITE="$BB_ENV_EXTRAWHITE BROWSER"

IMAGE_INSTALL_append = "\
   packagegroup-ml-middleware \
   packagegroup-ml-wpe \
   ${@bb.utils.contains('BROWSER', 'qt', 'packagegroup-ml-qt5browser', '', d)} \
"

#IMAGE_INSTALL_append_libc-glibc = " netflix "

