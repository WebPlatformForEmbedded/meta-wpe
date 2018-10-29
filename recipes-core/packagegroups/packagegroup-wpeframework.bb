DESCRIPTION = "Web Platform for Embedded Framework Packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-wpeframework \
"

RDEPENDS_packagegroup-wpeframework = "\
    wpeframework \
    wpeframework-plugins \
    wpeframework-ui \
"
