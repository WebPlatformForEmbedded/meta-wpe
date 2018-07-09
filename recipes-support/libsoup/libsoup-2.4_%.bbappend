FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-soup-cookie-jar-add-API-to-set-a-limit-of-cookies-in.patch"

EXTRA_OECONF += “--disable-gtk-doc \
                --disable-gtk-doc-html \
                --disable-dependency-tracking \
                --disable-static \
                --enable-shared \
                --disable-glibtest \
                --enable-vala=no \
                --without-gnome \
               ”