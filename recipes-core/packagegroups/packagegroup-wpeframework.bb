SUMMARY = "Web Platform for Embedded Framework Packagegroup"
DESCRIPTION = "Web Platform for Embedded Framework / Thunder Packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-wpeframework \
"

RDEPENDS_packagegroup-wpeframework = "\
    wpeframework \
    wpeframework-interfaces \
    wpeframework-clientlibraries \
    wpeframework-plugins \
    wpeframework-ui \
"

