SUMMARY = "RDK Reference APP UI"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cbffef879caf8b1db2d5173ff472c28c"

SRC_URI = "git://github.com/WebPlatformForEmbedded/RDKSplashScreen.git"
SRCREV = "3a6b89d62dbd407e4281a304078b7a97b49ae961"

S = "${WORKDIR}/git"

do_compile[noexec] = "1"

do_install() {
        install -d ${D}/var/www/RDKSplashScreen
        install -d ${D}/var/www/RDKSplashScreen/js
        install -d ${D}/var/www/RDKSplashScreen/js/polyfills/
        install -d ${D}/var/www/RDKSplashScreen/js/src.es5/
        install -d ${D}/var/www/RDKSplashScreen/js/src/

        install -d ${D}/var/www/RDKSplashScreen/static
        install -d ${D}/var/www/RDKSplashScreen/static/images
        install -d ${D}/var/www/RDKSplashScreen/static/images/assets/
        install -d ${D}/var/www/RDKSplashScreen/static/images/logo/

        install -d ${D}/var/www/RDKSplashScreen/static-ux
        install -d ${D}/var/www/RDKSplashScreen/static-ux/fonts/
        install -d ${D}/var/www/RDKSplashScreen/static-ux/tools/player/
        install -d ${D}/var/www/RDKSplashScreen/static-ux/tools/player/img/
   
	install -m 0755 ${S}/index.html ${D}/var/www/RDKSplashScreen
        install -m 0755 ${S}/metadata.json ${D}/var/www/RDKSplashScreen

        install -m 0755 ${S}/js/init.js ${D}/var/www/RDKSplashScreen/js/init.js
        install -m 0755 ${S}/js/polyfills/*.js ${D}/var/www/RDKSplashScreen/js/polyfills/
        install -m 0755 ${S}/js/src.es5/*.js ${D}/var/www/RDKSplashScreen/js/src.es5/
        install -m 0755 ${S}/js/src/*.js ${D}/var/www/RDKSplashScreen/js/src/


       install -m 0755 ${S}/static/images/assets/*.png ${D}/var/www/RDKSplashScreen/static/images/assets/
       install -m 0755 ${S}/static/images/logo/*.png ${D}/var/www/RDKSplashScreen/static/images/logo/

       install -m 0755 ${S}/static-ux/fonts/*.ttf ${D}/var/www/RDKSplashScreen/static-ux/fonts/
       install -m 0755 ${S}/static-ux/tools/player/img/*.png ${D}/var/www/RDKSplashScreen/static-ux/tools/player/img/
       install -m 0755 ${S}/static-ux/tools/player/.DS_Store ${D}/var/www/RDKSplashScreen/static-ux/tools/player/.DS_Store
}
