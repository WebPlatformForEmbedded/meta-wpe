SUMMARY = "WPE WebKit MESA backend"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=6ae4db0d4b812334e1539cd5aa6e2f46"

DEPENDS += "wpewebkit glib-2.0 libxkbcommon wayland virtual/libgl"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEBackend-mesa.git;protocol=http;branch=master"
SRCREV = "dd1636139631513ff93c4d92e81639a02754efdc"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

PACKAGECONFIG ?= "gbm dma-buf"

PACKAGECONFIG[gbm] = "-DWPE_MESA_GBM=ON,-DWPE_MESA_GBM=OFF,"
PACKAGECONFIG[dma-buf] = "-DWPE_MESA_EXPORTABLE_DMA_BUF=ON,-DWPE_MESA_EXPORTABLE_DMA_BUF=OFF,"
PACKAGECONFIG[experimental-wayland] = "-DWPE_MESA_EXPERIMENTAL_WAYLAND_EGL=ON,-DWPE_MESA_EXPERIMENTAL_WAYLAND_EGL=OFF,"
PACKAGECONFIG[tegra] = "-DWPE_MESA_DRM_TEGRA_SUPPORT=ON,-DWPE_MESA_DRM_TEGRA_SUPPORT=OFF,"

do_install() {

	install -d ${D}${libdir}
	install -m 0755 ${B}/libWPEBackend-*.so ${D}${libdir}/

}

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/libWPEBackend-default.so ${libdir}/libWPEBackend-mesa.so"
INSANE_SKIP ="dev-so"
