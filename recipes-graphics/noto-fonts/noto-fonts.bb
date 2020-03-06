SUMMARY = "Google font family called Noto, which aims to support all languages with a harmonious look and feel"
LICENSE = "OFL"
LIC_FILES_CHKSUM = "file://LICENSE_OFL.txt;md5=55719faa0112708e946b820b24b14097"

inherit allarch fontcache

SRC_URI = "https://noto-website-2.storage.googleapis.com/pkgs/Noto-unhinted.zip"
SRC_URI[md5sum] = "d26b29b10c3c8d05df4ade8286963722"
SRC_URI[sha256sum] = "7d0e099c208d11d7bf64091ea7f62f85bc07dedfaf2c01de53985a5b981025e3"

SRCNAME = "noto-fonts"
PV = "1.0"
S = "${WORKDIR}/${SRCNAME}-${PV}"

do_unpack() {
    unzip -q ${DL_DIR}/Noto-unhinted.zip -d ${S}
}

do_install () {
    install -d ${D}${datadir}/fonts/noto-fonts/
    if ${@bb.utils.contains("PACKAGECONFIG", "arabic", "true", "false", d)}
    then
        find ${S} |grep Arabic | grep -v Condensed |grep -v Extra |grep -v Semi | grep -v Light |grep -v Black |grep -v Thin |grep -v Medium | xargs -d "\n" cp -t ${D}${datadir}/fonts/noto-fonts/ ;
    fi
    if ${@bb.utils.contains("PACKAGECONFIG", "bengali", "true", "false", d)}
    then
        find ${S} |grep Bengali | grep -v Condensed |grep -v Extra |grep -v Semi | grep -v Light |grep -v Black |grep -v Thin |grep -v Medium | xargs -d "\n" cp -t ${D}${datadir}/fonts/noto-fonts/ ;
    fi
    if ${@bb.utils.contains("PACKAGECONFIG", "chinese", "true", "false", d)}
    then
        find ${S} |grep CJKsc | grep -v Condensed |grep -v Extra |grep -v Semi | grep -v Light |grep -v Black |grep -v Thin |grep -v Medium | xargs -d "\n" cp -t ${D}${datadir}/fonts/noto-fonts/ ;
    fi
    if ${@bb.utils.contains("PACKAGECONFIG", "ethiopic", "true", "false", d)}
    then
        find ${S} |grep Ethiopic | grep -v Condensed |grep -v Extra |grep -v Semi | grep -v Light |grep -v Black |grep -v Thin |grep -v Medium | xargs -d "\n" cp -t ${D}${datadir}/fonts/noto-fonts/ ;
    fi
    if ${@bb.utils.contains("PACKAGECONFIG", "georgian", "true", "false", d)}
    then
        find ${S} |grep Georgian | grep -v Condensed |grep -v Extra |grep -v Semi | grep -v Light |grep -v Black |grep -v Thin |grep -v Medium | xargs -d "\n" cp -t ${D}${datadir}/fonts/noto-fonts/ ;
    fi
    if ${@bb.utils.contains("PACKAGECONFIG", "hebrew", "true", "false", d)}
    then
        find ${S} |grep Hebrew | grep -v Condensed |grep -v Extra |grep -v Semi | grep -v Light |grep -v Black |grep -v Thin |grep -v Medium | xargs -d "\n" cp -t ${D}${datadir}/fonts/noto-fonts/ ;
    fi
    if ${@bb.utils.contains("PACKAGECONFIG", "japanese", "true", "false", d)}
    then
        find ${S} |grep CJKjp | grep -v Condensed |grep -v Extra |grep -v Semi | grep -v Light |grep -v Black |grep -v Thin |grep -v Medium | xargs -d "\n" cp -t ${D}${datadir}/fonts/noto-fonts/ ;
    fi
    if ${@bb.utils.contains("PACKAGECONFIG", "korean", "true", "false", d)}
    then
        find ${S} |grep CJKkr | grep -v Condensed |grep -v Extra |grep -v Semi | grep -v Light |grep -v Black |grep -v Thin |grep -v Medium | xargs -d "\n" cp -t ${D}${datadir}/fonts/noto-fonts/ ;
    fi
    if ${@bb.utils.contains("PACKAGECONFIG", "thai", "true", "false", d)}
    then
        find ${S} |grep Thai | grep -v Condensed |grep -v Extra |grep -v Semi | grep -v Light |grep -v Black |grep -v Thin |grep -v Medium | xargs -d "\n" cp -t ${D}${datadir}/fonts/noto-fonts/ ;
    fi
}

PACKAGECONFIG = "\
            arabic \
            bengali \
            chinese \
            ethiopic \
            georgian \
            hebrew \
            japanese \
            korean \
            thai"

FILES_${PN} = " \
    ${datadir}/fonts \
"
