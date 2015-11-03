SUMMARY = "Image containing proprietary and open source RDK components, including Qt"

require recipes-core/images/rdk-image-oss-noqt.bb

IMAGE_INSTALL += "\
    packagegroup-ml-wpe \
    packagegroup-ml-middleware \
"
