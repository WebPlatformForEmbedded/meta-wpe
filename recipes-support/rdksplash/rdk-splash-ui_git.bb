SUMMARY = "RDK Reference APP UI"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4ff33b11769ba14c6093567cd25ff3b2"

SRC_URI = "git://github.com/rdkcentral/RDKSplashScreen.git"
SRCREV = "7bf07c35cc531cd306c525b554310c48b3558138"

S = "${WORKDIR}/git"

do_compile[noexec] = "1"

do_install() {
        install -d ${D}/var/www/RDKSplashScreen
        cp -rf ${S}/dist/web/* ${D}/var/www/RDKSplashScreen/
}
