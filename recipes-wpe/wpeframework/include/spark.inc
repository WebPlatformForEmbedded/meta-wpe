# TBD find a way to fit the WPEFRAMEWORK platform in here...
SPARK_PLATFORM ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', ' -DPLATFORM_WAYLAND_EGL=ON', '', d)}"
SPARK_PLATFORM_remove = "${@bb.utils.contains('DISTRO_FEATURES', 'compositor', ' -DPLATFORM_WAYLAND_EGL=ON', '', d)}"
SPARK_PLATFORM_append = "${@bb.utils.contains('DISTRO_FEATURES', 'compositor', ' -DPLATFORM_WPEFRAMEWORK=ON', '', d)}"

WPE_SPARK_RESOLUTION       ?= "720p"
WPE_SPARK_RESOLUTION_rpi   ?= "720p"
WPE_SPARK_RESOLUTION_brcm  ?= "1080p"

PACKAGECONFIG[spark]        = "-DPLUGIN_SPARK=ON -DPLUGIN_SPARK_AUTOSTART=false -DPLATFORM_LINUX=ON ${SPARK_PLATFORM} -DPLUGIN_SPARK_RESOLUTION=${WPE_SPARK_RESOLUTION},-DPLUGIN_SPARK=OFF,pxcore-libnode,pxcore-libnode"
