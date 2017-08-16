DESCRIPTION = "Metrological WPE Packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-ml \
"

RDEPENDS_packagegroup-ml = "\
    cppsdk \
    webbridge \
"
