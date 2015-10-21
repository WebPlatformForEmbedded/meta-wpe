DESCRIPTION = "Metrological Basic Packagegroup"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

inherit packagegroup

PACKAGES = "\
    packagegroup-ml-middleware \
    packagegroup-ml-multimedia \
    packagegroup-ml-wpe \
    packagegroup-ml-qt5browser \
         "

RDEPENDS_packagegroup-ml-middleware = "\
    cppsdk \
    webbridge \
    dxdrm \ 
"

RDEPENDS_packagegroup-ml-multimedia = "\
"

RDEPENDS_packagegroup-ml-wpe = "\
"

RDEPENDS_packagegroup-ml-qt5browser = "\
    qtbase \
    qtbase-fonts \
    qtbase-plugins  \
    qtbrowser \
"
