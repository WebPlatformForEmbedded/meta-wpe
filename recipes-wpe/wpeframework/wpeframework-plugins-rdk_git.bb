SUMMARY = "WPEFramework Plugins RDK"
DESCRIPTION = "WPEFramework/Thunder Plugins RDK"
HOMEPAGE = "https://github.com:/WebPlatformForEmbedded/ThunderNanoServicesRDK"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=39fb5e7bc6aded7b6d2a5f5aa553425f"

PR = "r1"
PV = "3.0+git${SRCPV}"
RECIPE_BRANCH ?= "R4"
SRC_URI = "git://git@github.com:/WebPlatformForEmbedded/ThunderNanoServicesRDK.git;protocol=ssh;branch=${RECIPE_BRANCH}"
SRCREV ?= "7fb0b598ab6bd87b49235b99770a3162bb3691e1"

require include/wpeframework-plugins-rdk.inc

ASNEEDED = ""

