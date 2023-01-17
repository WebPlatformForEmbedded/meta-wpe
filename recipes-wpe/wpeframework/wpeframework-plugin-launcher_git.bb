SUMMARY = "WPE Framework Launcher Plugin"
DESCRIPTION = "WPE Framework Launcher Plugin. Plugin to launch external linux applications and scripts"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded/WPEPluginLauncher"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

require include/wpeframework-plugins.inc

PV = "3.0+gitr${SRCPV}"
RECIPE_BRANCH ?= "R4"
SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEPluginLauncher.git;branch=${RECIPE_BRANCH};protocol=https"
SRCREV ?= "6cd5c8f3bcef248d290f58c18a55cf54fc2d1b46"

