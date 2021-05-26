SUMMARY = "WPEFramework Plugins from RDKServices"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=39fb5e7bc6aded7b6d2a5f5aa553425f"
PR = "r1"
# Note has to be tested once the latest thunder changes synced with rdkcentral/rdkservices repo
SRC_URI = "git://git@github.com/rdkcentral/rdkservices.git;protocol=ssh;branch=main"
SRCREV = "9d02a5006449b795394623ba023d98c10a60bcf2"

require include/wpeframework-plugins-rdk.inc