SUMMARY = "interface to seccomp filtering mechanism"
DESCRIPTION = "\
    The libseccomp library provides and easy to use, platform independent, \
    interface to the Linux Kernel's syscall filtering mechanism: seccomp."
HOMEPAGE = "https://github.com/seccomp/libseccomp"
SECTION = "security"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE;beginline=0;endline=1;md5=8eac08d22113880357ceb8e7c37f989f"

SRC_URI = "git://github.com/seccomp/libseccomp.git;branch=release-2.4;protocol=https"
SRCREV = "1dde9d94e0848e12da20602ca38032b91d521427"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig ptest

PACKAGECONFIG ??= ""
PACKAGECONFIG[python] = "--enable-python, --disable-python, python"

DISABLE_STATIC = ""

do_compile_ptest() {
    oe_runmake -C tests check-build
}

FILES_${PN} += "${libdir}/${BPN}.so*"

