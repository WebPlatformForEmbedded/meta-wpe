SUMMARY = "RDK Reference APP UI"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=db266190918f8a7f7b27769754f98a75"

SRC_URI = "git://github.com/rdkcentral/RDKSplashScreen.git"
SRCREV = "38d312ebd40b847c6320cbc4d2d8de6824f5d653"

S = "${WORKDIR}/git"

do_compile[noexec] = "1"

do_install() {
        install -d ${D}/var/www/RDKSplashScreen
        cp -rf ${S}/dist/web/* ${D}/var/www/RDKSplashScreen/
        rm ${D}/var/www/RDKSplashScreen/LICENSE
}
