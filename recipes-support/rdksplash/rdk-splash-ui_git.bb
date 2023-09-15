SUMMARY = "RDK Reference APP UI"
DESCRIPTION = "UI Splash for RDK reference APP"
HOMEPAGE = "https://github.com/rdkcentral/RDKSplashScreen"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4ff33b11769ba14c6093567cd25ff3b2"

RDK_SPLASHSCREEN_URL ??= ""
RDK_SPLASHSCREEN_OPERATOR ??= "Metrological"

SRC_URI = "git://github.com/rdkcentral/RDKSplashScreen.git;protocol=https"
SRCREV = "7bf07c35cc531cd306c525b554310c48b3558138"

S = "${WORKDIR}/git"

do_compile[noexec] = "1"

do_install() {
    install -d "${D}/var/www/RDKSplashScreen"
    cp -arv --no-preserve=ownership "${S}/dist/web"/* "${D}/var/www/RDKSplashScreen/"
    if ${@bb.utils.contains("RDK_SPLASHSCREEN_OPERATOR", "", "true", "false", d)}
    then
        install -m 0644 "${S}/dist/web/static/config/config.operator.in" "${D}/var/www/RDKSplashScreen/config.json"
        sed -i -e "s|%operator%|${RDK_SPLASHSCREEN_OPERATOR}|g" "${D}/var/www/RDKSplashScreen/config.json"
    fi
    if ${@bb.utils.contains("RDK_SPLASHSCREEN_URL", "", "true", "false", d)}
    then
        install -m 0644 "${S}/dist/web/static/config/config.url.in" "${D}/var/www/RDKSplashScreen/config.json"
        sed -i -e "s|%operator%|${RDK_SPLASHSCREEN_URL}|g" "${D}/var/www/RDKSplashScreen/config.json"
    fi
}

FILES_${PN} += "/var/www/RDKSplashScreen/"
