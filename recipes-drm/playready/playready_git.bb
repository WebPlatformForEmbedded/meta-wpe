SUMMARY = "Microsoft PlayReady DRM implementation."
HOMEPAGE = "https://www.microsoft.com/playready/"
LICENSE = "CLOSED"

DEPENDS = "libprovision"

SRCREV = "3f1ed46727fa51fc39135b8545857784a109f92e"
PV = "1.0.gitr${SRCPV}"
SRC_URI = "git://git@github.com/Metrological/playready.git;protocol=ssh"

inherit pkgconfig cmake

EXTRA_OECMAKE += " \
	-DCMAKE_BUILD_TYPE=Release \
	-DCMAKE_C_FLAGS="-std=c99 -D_GNU_SOURCE ${TARGET_CC_ARCH} ${TOOLCHAIN_OPTIONS}" \
	-DPLAYREADY_USE_PROVISION=ON \
"

S = "${WORKDIR}/git/src"

do_install_append() {
	install -d ${B}/package/playready/playready.pc \
	${D}${libdir}/pkgconfig/playready.pc

	install -d ${D}${sysconfdir}/playready/storage

	if [ -f ${B}/package/playready/bgroupcert.dat ]; then \
		install -D -m 0644 ${B}/package/playready/bgroupcert.dat ${D}${sysconfdir}/playready/; \
	fi
	if [ -f ${B}/package/playready/zgpriv.dat ]; then \
		$(INSTALL) -D -m 0644 ${B}/package/playready/zgpriv.dat ${D}${sysconfdir}/playready/; \
	fi
	ln -sf /tmp ${D}${sysconfdir}/playready/storage
}
