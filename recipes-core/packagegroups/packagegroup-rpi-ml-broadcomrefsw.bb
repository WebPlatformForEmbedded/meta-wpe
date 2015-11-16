DESCRIPTION = "Metrological broadcom specific Packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-ml-broadcomrefsw \
"

RDEPENDS_packagegroup-ml-broadcomrefsw = "\
    broadcom-refsw \
"

# Additional OSS packages etc, which are only needed for broadcom_refsw based images.
RDEPENDS_packagegroup-ml-broadcomrefsw += ""
