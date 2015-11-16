SUMMARY = "Image containing proprietary and open source RDK components and WPE browser for Broadcom boxes"

require recipes-core/images/rdk-image-oss-noqt.bb

PREFERRED_VERSION_stblinux = "3.14-1.6"
PREFERRED_VERSION_broadcom-refsw = "15.2+git%"

IMAGE_INSTALL += "\
    packagegroup-ml-broadcomrefsw \
    packagegroup-ml-wpe \
    packagegroup-ml-middleware \
"
