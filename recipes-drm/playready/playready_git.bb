SUMMARY = "Microsoft PlayReady DRM implementation."
HOMEPAGE = "https://www.microsoft.com/playready/"
LICENSE = "CLOSED"

DEPENDS = "cppsdk libprovision"

PV = "1.0.gitr${SRCPV}"

SRCREV = "3f1ed46727fa51fc39135b8545857784a109f92e"

SRC_URI = "git://git@github.com/Metrological/playready.git;protocol=ssh"
SRC_URI += "file://playready.pc"

inherit pkgconfig cmake

S = "${WORKDIR}/git/src"

EXTRA_OECMAKE += " \
	-DCMAKE_BUILD_TYPE=Release \
	-DCMAKE_C_FLAGS="-std=c99 -D_GNU_SOURCE ${TARGET_CC_ARCH} ${TOOLCHAIN_OPTIONS}" \
	-DPLAYREADY_USE_PROVISION=ON \
	-DBUILD_SHARED_LIBS=ON \
"

do_install_append() {
        install -d ${D}${libdir}/pkgconfig
        install -m 0644 ${WORKDIR}/playready.pc ${D}${libdir}/pkgconfig/
        
	install -d ${D}${sysconfdir}/playready
	if [ -f ${B}/package/playready/bgroupcert.dat ]; then
		install -m 0644 ${B}/package/playready/bgroupcert.dat ${D}${sysconfdir}/playready/
	fi
	if [ -f ${B}/package/playready/zgpriv.dat ]; then
		install -m 0644 ${B}/package/playready/zgpriv.dat ${D}${sysconfdir}/playready/
	fi

	# Fixme: stuff related to "storage" should probably be under /var, not /etc
	ln -sf /tmp ${D}${sysconfdir}/playready/storage
}

# libplayready.so isn't versioned, so we need to force .so files into the
# run-time package (and keep them out of the -dev package).

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

FILES_${PN} += "${sysconfdir}/playready"

# Autodetection of runtime dependency on libprovision.so doesn't work because
# libprovision.so is linked without "-Wl,-soname,libprovision.so".
# See http://tldp.org/HOWTO/Program-Library-HOWTO/shared-libraries.html
# Until that's fixed, specify the runtime dependency manually.
RDEPENDS_${PN} += "libprovision"
