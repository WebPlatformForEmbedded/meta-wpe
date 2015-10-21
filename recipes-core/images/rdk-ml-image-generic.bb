SUMMARY = "Image containing proprietary and open source RDK components, including Qt"

require recipes-core/images/rdk-image-oss-noqt.bb

IMAGE_INSTALL += "\
    packagegroup-ml-qt5browser \
    packagegroup-ml-middleware \
"
