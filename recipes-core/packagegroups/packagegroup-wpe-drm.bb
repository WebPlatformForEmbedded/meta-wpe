DESCRIPTION = "Web Platform for Embedded Packagegroup for the OpenCDMs"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-wpe-drm \
"

RDEPENDS_packagegroup-wpe-drm = "\
    wpeframework \
    wpeframework-plugins \
    wpeframework-provisioning \
    wpeframework-ocdm-clearkey \
    wpeframework-ocdm-playready \
"
