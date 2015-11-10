DESCRIPTION = "Metrological broadcom specific Packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-ml-broadcom_refsw \
"

RDEPENDS_packagegroup-ml-broadcom_refsw = "\
    broadcom-refsw \
"

# Additional OSS packages etc, which are only needed for broadcom_refsw based images.
RDEPENDS_packagegroup-ml-broadcom_refsw += ""
