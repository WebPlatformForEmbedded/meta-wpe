# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Provisioning support library"
HOMEPAGE = "https://github.com/Metrological/libprovision"
SECTION = "libs"
LICENSE = "CLOSED"

DEPENDS = "openssl"

PV = "2.0.gitr${SRCPV}"

SRCREV = "eed5b430952123a79285eefeb7309b2545aa91fa"
SRC_URI = "git://git@github.com/Metrological/libprovision.git;protocol=ssh;branch=master"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

COMPATIBLE_HOST = '(i.86|arm|mipsel).*-linux'

PROV_ARCH ?= ""
PROV_ARCH_arm = "arm"
PROV_ARCH_x86 = "i686"
PROV_ARCH_mipsel = "mipsel"

do_install_prepend() {
	mkdir -p ${D}${libdir}
}

# Add the libraries to the correct package
FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/lib*.a"

INSANE_SKIP_${PN} += "already-stripped"
# Some Damage control:
# ldflags is added due to the .so being not compiled with gnu_hash style on ARM
# whoever provides these precompiled objects should note to add
# "-Wl,--hash-style=gnu" to LDFLAGS when generating these objects
INSANE_SKIP_${PN}_append_arm = " ldflags"
