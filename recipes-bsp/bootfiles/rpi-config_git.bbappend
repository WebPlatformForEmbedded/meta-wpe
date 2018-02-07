do_deploy_append() {
    if [ -n "${GPU_FREQ}" ]; then
        sed -i '/#gpu_freq=/ c\gpu_freq=${GPU_FREQ}' ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
    fi

    if [ -n "${HDMI_GROUP}" ]; then
        sed -i '/#hdmi_group=/ c\hdmi_group=${HDMI_GROUP}' ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
    fi

    if [ -n "${HDMI_MODE}" ]; then
        sed -i '/#hdmi_mode=/ c\hdmi_mode=${HDMI_MODE}' ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
    fi

    if [ -n "${DISABLE_SPLASH}" ]; then
        sed -i '/#disable_splash=/ c\disable_splash=${DISABLE_SPLASH}' ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
    fi

    if [ -n "${BOOT_DELAY}" ]; then
        sed -i '/#boot_delay=/ c\boot_delay=${BOOT_DELAY}' ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
    fi
}
