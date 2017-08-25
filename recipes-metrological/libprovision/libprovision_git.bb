# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Provisioning support library"
HOMEPAGE = "https://github.com/Metrological/libprovision"
SECTION = "libs"
LICENSE = "CLOSED"

DEPENDS = "cppsdk openssl"

PV = "1.0.gitr${SRCPV}"

SRCREV = "b8d14e82101f9c5f7ef73d7d33927009298d0612"
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
FILES_${PN} += "${libdir}/lib*.so"
INSANE_SKIP_${PN} += "already-stripped"
# Some Damage control:
# ldflags is added due to the .so being not compiled with gnu_hash style on ARM
# whoever provides these precompiled objects should note to add
# "-Wl,--hash-style=gnu" to LDFLAGS when generating these objects
INSANE_SKIP_${PN}_append_arm = " ldflags"
