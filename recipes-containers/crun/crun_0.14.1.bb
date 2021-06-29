SUMMARY = "A fast and lightweight fully featured OCI runtime and C library for running containers"
DESCRIPTION = " \
    A fast and low-memory footprint OCI Container Runtime fully written in C.\
    crun conforms to the OCI Container Runtime specifications"
HOMEPAGE = "https://github.com/containers/crun"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS_append = " libcap libseccomp libtool yajl"
PV = "0.13.1"

SRC_URI = "\
    gitsm://github.com/containers/crun \
    file://0001-Preparing-for-library-build.patch \
    file://0002-Fix-cpuacct-not-beeing-created.patch \
"
SRCREV = "e79e4de4ac16da0ce48777afb72c6241de870525"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig python3native

do_configure_prepend () {
    cd ${S}
    ./autogen.sh
}

# Force bitbake to ensure libocispec has been compiled before compiling crun
# Fix random build failure due to race condition in Jenkins
do_compile_prepend() {
    cd ${S}/libocispec
    oe_runmake
    cd ${S}
}

# Don't need systemd integration, so disable it to remove dependency on libsystemd
EXTRA_OECONF = "--disable-systemd"
EXTRA_OECONF_append = " --enable-shared"
PARALLEL_MAKE = ""

