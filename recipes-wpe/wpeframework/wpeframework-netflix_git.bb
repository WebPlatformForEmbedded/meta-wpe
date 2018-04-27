SUMMARY = "WPE Framework Netflix plugin"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "CLOSED"

require wpeframework-plugins.inc

DEPENDS += " netflix libprovision"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/WPEPluginNetflix.git;protocol=ssh;branch=master \
		   file://0001-cmake-Remove-redundant-include.patch"
SRCREV = "9735202436138fd1438d47cdd5e17618290990ff"

PACKAGECONFIG[hd]               = "-DWPEFRAMEWORK_PLUGIN_NETFLIX_FULLHD=true,-DWPEFRAMEWORK_PLUGIN_NETFLIX_FULLHD=false,"

RDEPENDS_${PN} = "wpeframework-dialserver wpeframework-provisioning"
