SUMMARY = "Image containing proprietary and open source RDK components and WPE browser"

require recipes-core/images/rdk-image-oss-noqt.bb

PREFERRED_VERSION_broadcom-refsw = "15.2+git%"

IMAGE_INSTALL += "\
    packagegroup-ml-broadcom_refsw \
    packagegroup-ml-wpe \
    packagegroup-ml-middleware \
"
