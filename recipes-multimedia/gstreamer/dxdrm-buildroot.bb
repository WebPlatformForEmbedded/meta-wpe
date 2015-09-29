LICENSE = "GPL"

SRC_URI = "file://${HOME}/Projects/yoct_projs/buildroot/dxdrm-buildroot.tar;unpack=true"

LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
S = "${WORKDIR}/dxdrm-buildroot"

do_install() {
# Installing the header files and the library in sysroot. This will enable
# other components to access dxdrm functionality.

	install -d ${D}${includedir}/dxdrm/
	install -d ${D}${bindir}
	install -d ${D}${libdir}

	for f in ${S}/include/*.h; do
		install -m 0644 $f ${D}${includedir}/dxdrm
	done

	for f in ${S}/i686/release/*.so; do
		install -m 0644 $f ${D}${libdir}/
        done
}

