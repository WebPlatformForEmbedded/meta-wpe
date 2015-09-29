# meta-metrological
Metrological layer to be used within a Yocto build.

Yocto Metrological Layer - RaspberryPi(2)
================================

This layer depends on:

URI: git://git.yoctoproject.org/poky
branch: master
revision: HEAD

URI: git://git.openembedded.org/meta-openembedded
layers: meta-oe, meta-multimedia
branch: master
revision: HEAD

URI: git://git.yoctoproject.org/meta-raspberrypi
layers: meta-raspberrypi
branch: master
revision: HEAD

How to use it:

1. source poky/oe-init-build-env rpi-ml-build
2. Add needed layer to bblayers.conf:
    - meta-raspberrypi
    - meta-metrological
3  Set MACHINE to "raspberrypi"/"raspberrypi2" in local.conf
4. bitbake rpi-basic-ml-image
5. dd to a SD card the generated sdimg file (use xzcat if rpi-sdimg.xz is used)
6. Boot your RPI.

