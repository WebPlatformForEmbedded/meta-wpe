DESCRIPTION = "Metrological QTbrowser Packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-ml-qt5browser \
"

RDEPENDS_packagegroup-ml-qt5browser = "\
    qtbase \
    qtbase-fonts \
    qtbase-plugins  \
    qtbrowser \
"
