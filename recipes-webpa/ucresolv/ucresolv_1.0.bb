SUMMARY = "ucresolv library"
DESCRIPTION = "Recipe to build library for uclibc resolver missing features"
HOMEPAGE = "https://github.com/Comcast/libucresolv"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRCREV = "996c3778b14936c26b49e30f8dbb4933cb2df49a"
SRC_URI = "\
    git://github.com/Comcast/libucresolv.git \
    file://0001-use-headers-from-sysroot.patch \
    file://0002-ucresolv_error.patch \
"
PV = "git+${SRCPV}"
S = "${WORKDIR}/git"
PR = "r1"

inherit cmake pkgconfig
lcl_maybe_fortify = ""
EXTRA_OECMAKE_append = "\
    -DBUILD_TESTING=OFF \
    -DBUILD_YOCTO=true \
    -D_FORTIFY_SOURCE=0 \
"
CFLAGS_append = " -I${S}/include/wcsmbs"

FILES_${PN} += "${libdir}/*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} += "dev-so"

