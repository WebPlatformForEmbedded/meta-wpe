PACKAGECONFIG:remove = "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', 'wayland', d)}"
PACKAGECONFIG:append = " opusparse faad"

