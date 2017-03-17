
# **meta-wpe**

`meta-wpe` is a meta layer for OpenEmbedded / Yocto environments. It provides the necessary recipes to build the WebPlatformforEmbedded components including the WPE WebKit browser.

# Setup
Note: You only need this if you do not have an existing OpenEmbedded Yocto environment.

create a workspace for your yocto build:

`$ mkdir -p rpi-yocto`

Inside the newly created directory, initiate the repository

`$ repo init -u ssh://git@github.com/WebPlatformForEmbedded/meta-wpe.git -b master -m tools/manifests/rpi-yocto.xml`

Fetch and sync all repositories:

`$ repo sync`

# Build
Initiate OE by invoking the oe init script:

`$ source poky/oe-init-build-env rpi-ml-build`

Add bb layers:

```
$ bitbake-layers add-layer ../meta-raspberrypi

$ bitbake-layers add-layer ../meta-wpe

$ bitbake-layers add-layer ../meta-openembedded/meta-oe/

$ bitbake-layers add-layer ../meta-openembedded/meta-multimedia/
```


Edit `conf/local.conf` and set the target machine:
`MACHINE = "raspberrypi3"`
you can use raspberrypi2 as well if you own raspberrypi2 machine.

Set GCC to use version 5.4:

`GCC_VERSION_forcevariable = "5.4%"`

Note: GCC 6.x is supported but experimental at this stage. Mileage may vary.

Build WPE with Westeros Compositor:

`$ bitbake westeros-wpe-image`

# Install

To flash the sdimg on the sd card:

`$ sudo dd if=tmp/deploy/images/raspberrypi3/westeros-wpe-image-raspberrypi3.rpi-sdimg of=/dev/sdX`



# Optional settings

Use 4.9 kernel (optional), edit `conf/local.conf` and set:

`PREFERRED_VERSION_linux-raspberrypi = "4.9%"`

To build without Westeros/Wayland support on RPI (fullscreen EGL), edit `conf/local.conf` and set:
```
WPE_BACKEND_rpi  = "rpi"
DISTRO_FEATURES_remove = "wayland"
```

*Note* WPE has many other view backends it can use. To select specific setting, like BCM Nexus or IntelCE edit your `conf/local.conf` and set the `WPE_BACKEND = foo` directly.


To build an initramfs image, edit `conf/local.conf` and set:

```
INITRAMFS_IMAGE_BUNDLE_rpi = "1"
INITRAMFS_IMAGE_rpi = "wpe-initramfs-image"
KERNEL_INITRAMFS = "-initramfs" BOOT_SPACE = "163840"
```

To install the initram fs, copy the zImage to the fat32 partition of your RPI.




