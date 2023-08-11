SUMMARY = "WPE Framework Launcher Plugin"
DESCRIPTION = "WPE Framework Launcher Plugin. Plugin to launch external linux applications and scripts"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded/WPEPluginLauncher"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

require include/wpeframework-plugins.inc

PV = "3.0+gitr${SRCPV}"
RECIPE_BRANCH ?= "R4"
SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEPluginLauncher.git;branch=${RECIPE_BRANCH};protocol=https"
SRCREV ?= "5d9a5c45bba36e503aff93db08ca98deec7591e9"

