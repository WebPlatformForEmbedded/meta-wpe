SUMMARY = "RDK Reference APP UI"
DESCRIPTION = "UI Splash for RDK reference APP"
HOMEPAGE = "https://github.com/rdkcentral/RDKSplashScreen"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4ff33b11769ba14c6093567cd25ff3b2"

SRC_URI = "git://github.com/rdkcentral/RDKSplashScreen.git"
SRCREV = "38d312ebd40b847c6320cbc4d2d8de6824f5d653"

S = "${WORKDIR}/git"

do_compile[noexec] = "1"

do_install() {
        install -d ${D}/var/www/RDKSplashScreen
        cp -rf ${S}/* ${D}/var/www/RDKSplashScreen
        rm ${D}/var/www/RDKSplashScreen/LICENSE
}

