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
    - meta-raspberrypi: `bitbake-layers add-layer ../meta-raspberrypi`
    - meta-metrological: `bitbake-layers add-layer ../meta-metrological`
    - meta-openembedded/meta-oe: `bitbake-layers add-layer ../meta-openembedded/meta-oe/`
    - meta-openembedded/meta-multimedia: `bitbake-layers add-layer ../meta-openembedded/meta-multimedia/` 
3. Set MACHINE to "raspberrypi"/"raspberrypi2" in conf/local.conf. (see note on sdl):
    `echo 'MACHINE = "raspberrypi2"' >> conf/local.conf`
4. By default this recipe builds WPE for RPI. If you want to build QT please set the following two environment variables:
	`echo BROWSER=qt`
	`export BB_ENV_EXTRAWHITE="$BB_ENV_EXTRAWHITE BROWSER"`	
4. bitbake rpi-hwup-image
5. dd to a SD card the generated sdimg file (use xzcat if rpi-sdimg.xz is used)
6. Boot your RPI.

**note**
If you get the following error: 
`libsdl-native is set to be ASSUME_PROVIDED but sdl-config can't be found in PATH. Please either install it, or configure qemu not to require sdl.`

Make sure the following is unset in conf/local.conf:

```
PACKAGECONFIG_append_pn-qemu-native = ""
PACKAGECONFIG_append_pn-nativesdk-qemu = ""
ASSUME_PROVIDED += ""
```
