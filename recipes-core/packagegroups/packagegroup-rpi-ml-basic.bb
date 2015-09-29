DESCRIPTION = "Metrological Basic Packagegroup"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

inherit packagegroup

PACKAGES = "\
    packagegroup-ml-basic \
    packagegroup-ml-test \
         "

RDEPENDS_packagegroup-ml-basic = "\
    cppsdk \
"

RDEPENDS_packagegroup-ml-test = "\
     \
"
