SUMMARY = "Bitmovin libdash"
DESCRIPTION = "libdash is the official reference software of the ISO/IEC MPEG-DASH standard\
    and is an open-source library that provides an object orient (OO) interface to the MPEG-DASH\
    standard, developed by Bitmovin"
HOMEPAGE = "https://github.com/bitmovin/libdash"
LICENSE = "LGPLv2.1"
SECTION = "libs"

# Setting to closed even though it is not. Libdash does not seem to have a license file in the repository
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS_append = " curl libxml2 zlib"

PV = "3.0.gitr${SRCPV}"

SRC_URI = "\
    git://github.com/bitmovin/libdash;branch=stable_3_0;protocol=https \
    file://libdash.pc \
    file://0001-libdash-build.patch \
    file://0002-libdash-starttime-uint64.patch \
    file://0003-libdash-presentationTimeOffset-uint64.patch \
    file://0004-Support-of-EventStream.patch \
"

SRCREV = "f5b5d991af5fe5f285e8040c997b755d3d456b0d"

S = "${WORKDIR}/git/libdash"

inherit cmake pkgconfig

do_install_append() {
	install -d ${D}${libdir}/pkgconfig
	install -m 0644 ${WORKDIR}/libdash.pc ${D}${libdir}/pkgconfig

	mkdir -p ${D}${includedir}/libdash
	mkdir -p ${D}${includedir}/libdash/helpers
	mkdir -p ${D}${includedir}/libdash/mpd
	mkdir -p ${D}${includedir}/libdash/network
	mkdir -p ${D}${includedir}/libdash/portable
	mkdir -p ${D}${includedir}/libdash/metrics
	mkdir -p ${D}${includedir}/libdash/xml
	install -D -m 0755 ${S}/libdash/include/*.h ${D}${includedir}/libdash/
	install -D -m 0755 ${S}/libdash/source/helpers/*.h ${D}${includedir}/libdash/helpers/
	install -D -m 0755 ${S}/libdash/source/mpd/*.h ${D}${includedir}/libdash/mpd/
	install -D -m 0755 ${S}/libdash/source/network/*.h ${D}${includedir}/libdash/network/
	install -D -m 0755 ${S}/libdash/source/portable/*.h ${D}${includedir}/libdash/portable/
	install -D -m 0755 ${S}/libdash/source/metrics/*.h ${D}${includedir}/libdash/metrics/
	install -D -m 0755 ${S}/libdash/source/xml/*.h ${D}${includedir}/libdash/xml/
}

FILES_${PN} += "${libdir}/libdash.so"
FILES_${PN}-dbg += "${includedir}/*"
FILES_${PN}-dbg += "${libdir}/pkgconfig/*"
PACKAGES = "${PN} ${PN}-dbg"

INSANE_SKIP_${PN} = "dev-deps"
