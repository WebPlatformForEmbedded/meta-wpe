# Common for thunder and tools
HOMEPAGE = "https://github.com/rdkcentral/Thunder"
SECTION = "wpe"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=85bcfede74b96d9a58c6ea5d4b607e58"

RECIPE_BRANCH ?= "R3"
SRC_URI[md5sum] = "42b518b9ccd6852d1d709749bc96cb70"
SRC_URI[sha256sum] = "f3c45b121cf6257eafabdc3a8008763aed1cd7da06dbabc59a9e4d2a5e4e6697"
SRC_URI = "git://github.com/rdkcentral/Thunder.git;protocol=git;branch=${RECIPE_BRANCH};protocol=https"
SRCREV ?= "c4ed45e588780768b9fc77ad072b2a2f040912ce"
PR = "r0"
S = "${WORKDIR}/git"

