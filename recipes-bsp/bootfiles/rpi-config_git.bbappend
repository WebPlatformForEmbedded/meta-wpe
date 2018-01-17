do_deploy_prepend() {
    ARM_FREQ=1000
    GPU_MEM_256=128
    GPU_MEM_512=196
    GPU_MEM_1024=256
}

do_deploy_append() {
    sed -i '/#gpu_freq/ c\gpu_freq=500' ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
}
