do_deploy_prepend() {
    ARM_FREQ=1000
    GPU_MEM_256=128
    GPU_MEM_512=196
    GPU_MEM_1024=384
    OVER_VOLTAGE=6
    SDRAM_FREQ=500
}

do_deploy_append() {
    sed -i '/#disable_splash=/ c\disable_splash=1' ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
    sed -i '/#gpu_freq=/ c\gpu_freq=500' ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
    sed -i '/#disable_overscan=/ c\disable_overscan=1' ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
    sed -i '/#boot_delay=/ c\boot_delay=0' ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
}
