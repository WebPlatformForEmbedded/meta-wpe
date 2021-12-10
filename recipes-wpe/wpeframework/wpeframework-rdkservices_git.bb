SUMMARY = "WPEFramework Plugins from RDKServices"
DESCRIPTION = "WPEFramework Plugins from RDKServices from Thunder"
HOMEPAGE = "https://github.com/rdkcentral/rdkservices"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=39fb5e7bc6aded7b6d2a5f5aa553425f"

PR = "r1"
PV = "3.0+git${SRCPV}"
# Note has to be tested once the latest thunder changes synced with rdkcentral/rdkservices repo
RECIPE_BRANCH ?= "main"
SRC_URI = "git://git@github.com/rdkcentral/rdkservices.git;protocol=ssh;branch=${RECIPE_BRANCH}"
SRCREV ?= "8acdb5d4c8e85e5b04c2a519dd4f4eefcc546fe3"

require include/wpeframework-plugins-rdk.inc
