SUMMARY = "RDK Reference APP UI"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=db266190918f8a7f7b27769754f98a75"

SRC_URI = "git://github.com/WebPlatformForEmbedded/RDKSplashScreen.git"
SRCREV = "74a27537d11fede7cd0fa6b094db580e08796bcd"

S = "${WORKDIR}/git"

do_compile[noexec] = "1"

do_install() {
        install -d ${D}/var/www/RDKSplashScreen
        cp -rf ${S}/* ${D}/var/www/RDKSplashScreen/  
        rm ${D}/var/www/RDKSplashScreen/LICENSE
}
