# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Provisioning support library"
HOMEPAGE = "https://github.com/Metrological/libprovision"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"
SECTION = "libs"
DEPENDS = "cppsdk openssl"

SRCREV = "3b68f177bf8ec51b3c36ba1e7e160a349ede328d"
PV = "1.0.gitr${SRCPV}"
SRC_URI = "git://git@github.com/Metrological/libprovision.git;protocol=ssh"
# Set dxdrm config

PROV_ARCH ?= ""
PROV_ARCH_x86 = "i686"
PROV_ARCH_arm = "arm"

S = "${WORKDIR}/git"

do_compile[noexec] = "1"

do_install() {
    install -Dm 755 ${S}/${PROV_ARCH}/libprovision.so ${D}${libdir}/libprovision.so
    install -Dm 755 ${S}/${PROV_ARCH}/libprovisionproxy.so ${D}${libdir}/libprovisionproxy.so
    install -Dm 644 ${S}/provision.pc ${D}${libdir}/pkgconfig/provision.pc

    install -d  ${D}${includedir}/rpc
    install -m 644 ${S}/include/rpc/*.h ${D}${includedir}/rpc

    install -d  ${D}${includedir}/provision
    install -m 644 ${S}/include/provision/*.h ${D}${includedir}/provision
}

# Add the libraries to the correct package
FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/lib*.so"
INSANE_SKIP_${PN} += "already-stripped"
# Some Damage control:
# ldflags is added due to the .so being not compiled with gnu_hash style on ARM
# whoever provides these precompiled objects should note to add
# "-Wl,--hash-style=gnu" to LDFLAGS when generating these objects
INSANE_SKIP_${PN}_append_arm = " ldflags"

