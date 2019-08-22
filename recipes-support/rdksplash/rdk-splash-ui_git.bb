SUMMARY = "RDK Reference APP UI"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cbffef879caf8b1db2d5173ff472c28c"

SRC_URI = "git://github.com/WebPlatformForEmbedded/RDKSplashScreen.git"
SRCREV = "25d6642978b3fd84ac97dd0beb2655a051b5aca0"

S = "${WORKDIR}/git"

do_compile[noexec] = "1"

do_install() {
        install -d ${D}/var/www/RDKSplashScreen
        cp -rf ${S}/* ${D}/var/www/RDKSplashScreen/  
        rm ${D}/var/www/RDKSplashScreen/LICENSE
}
