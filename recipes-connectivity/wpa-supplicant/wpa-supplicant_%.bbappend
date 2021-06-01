do_configure_append () {
    if "${@bb.utils.contains_any('DISTRO_FEATURES', [ 'thunder_debug', 'thunder_debugoptimized' ], 'true', 'false', d)}"
    then
        if grep -q '\bCONFIG_DEBUG_FILE\b' wpa_supplicant/.config; then
            sed -i -e '/\bCONFIG_DEBUG_FILE\b/s/.*/CONFIG_DEBUG_FILE=y/' wpa_supplicant/.config
        else
            echo "CONFIG_DEBUG_FILE=y" >> wpa_supplicant/.config
        fi
    fi
}

