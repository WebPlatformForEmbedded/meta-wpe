DESCRIPTION = "Recipe to build nopoll"
HOMEPAGE = "http://www.aspl.es/nopoll/"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=f0504124678c1b3158146e0630229298"

DEPENDS_append = " openssl"
LDFLAGS_append = " -pthread"
SRCREV = "41f9cd37cf3bf657a598e2330d99aad316f8d0dd"

SRC_URI[md5sum] = "0f1ee40491e69d09b354771c6d44bb34"
SRC_URI[sha256sum] = "a1a25dcfe8406fcd355568ab0331f24eeec011a7b272df7b34a16db4a283b834"

SRC_URI = "git://github.com/Comcast/nopoll.git;branch=nopoll_yocto"
SRC_URI_append_morty = " file://libnopoll.pc"

inherit autotools pkgconfig

S = "${WORKDIR}/git"

do_install_append_morty() {
    install -Dm644 ${S}/../libnopoll.pc ${D}${libdir}/pkgconfig/libnopoll.pc
}

FILES_${PN} = "${bindir} ${libdir}/lib*${SOLIBS}"

