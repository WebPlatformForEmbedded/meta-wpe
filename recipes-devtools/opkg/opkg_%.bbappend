do_install_append () {
        install -d ${D}${includedir}/libopkg
        install -m 0644 ${S}/libopkg/*.h ${D}${includedir}/libopkg
}
