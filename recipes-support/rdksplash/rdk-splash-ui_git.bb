SUMMARY = "RDK Reference APP UI"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cbffef879caf8b1db2d5173ff472c28c"

SRC_URI = "git://github.com/WebPlatformForEmbedded/RDKSplashScreen.git"
SRCREV = "3a6b89d62dbd407e4281a304078b7a97b49ae961"

S = "${WORKDIR}/git"

do_compile[noexec] = "1"

do_install() {
        install -d ${D}/var/www/RDKSplashScreen
        cp -rf ${S}/* ${D}/var/www/RDKSplashScreen/  
        rm ${D}/var/www/RDKSplashScreen/LICENSE
}
