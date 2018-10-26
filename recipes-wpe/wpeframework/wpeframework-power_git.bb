SUMMARY = "WPE Framework Power Control plugin"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

DEPENDS += " ${@bb.utils.contains('PREFERRED_PROVIDER_virtual/egl', 'broadcom-refsw', 'broadcom-refsw', '', d)}"

require wpeframework-plugins.inc

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/WPEPluginPower.git;protocol=ssh;branch=master"
SRCREV = "23940c8f5104fd2748f9925fcd246ba6a43311fd"
