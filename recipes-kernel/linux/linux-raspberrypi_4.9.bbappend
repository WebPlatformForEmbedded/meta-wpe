FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://0001-Adding-metrological-boot-logo.patch \
    file://logo_metrological_clut224.ppm \
"

do_configure_prepend() {
    kernel_configure_variable LOGO_LINUX_CLUT224 n
    kernel_configure_variable LOGO_METROLOGICAL_CLUT224 y

    cp ${WORKDIR}/logo_metrological_clut224.ppm ${B}/source/drivers/video/logo/logo_metrological_clut224.ppm
}
