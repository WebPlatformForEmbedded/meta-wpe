# Provides configuration for the Spark Plugin

def platformselector(d):
    wayland = bb.utils.contains('DISTRO_FEATURES', 'wayland', True, False, d)
    compositor = bb.utils.contains('DISTRO_FEATURES', 'compositor', True, False, d)

    if compositor == False and wayland == False:
        return ''
    elif compositor == False and wayland == True:
        return ' -DPLATFORM_WAYLAND_EGL=ON'
    else:
        return ' -DPLATFORM_WPEFRAMEWORK=ON'

WPE_SPARK_PLATFORM = "${@platformselector(d)}"
WPE_SPARK_RESOLUTION ??= "720p"

PACKAGECONFIG[spark] = "\ 
    -DPLUGIN_SPARK=ON \
    -DPLUGIN_SPARK_AUTOSTART=false \
    -DPLATFORM_LINUX=ON \
    -DPLUGIN_SPARK_RESOLUTION=${WPE_SPARK_RESOLUTION} \
    ${WPE_SPARK_PLATFORM} \
    ,-DPLUGIN_SPARK=OFF,pxcore-libnode spark,pxcore-libnode spark \
"
#INSANE_SKIP_${PN} += "dev-deps"
