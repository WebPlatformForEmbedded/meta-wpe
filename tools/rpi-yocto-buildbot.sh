#!/bin/bash
# Bootstrapper for buildbot slave

DIR="build"
MACHINE="raspberrypi2"
CONFFILE="conf/auto.conf"
BITBAKEIMAGE="virtual/kernel"

# clean up the output dir
echo "Cleaning build dir"
rm -rf $DIR

# make sure sstate is there
echo "Creating sstate directory"
mkdir -p ~/sstate/rpi

# fix permissions set by buildbot
echo "Fixing permissions for buildbot"
umask -S u=rwx,g=rx,o=rx
chmod -R 755 .

# bootstrap OE
echo "Init OE"
export BASH_SOURCE="poky/oe-init-build-env"
. poky/oe-init-build-env $DIR

# Symlink the cache
echo "Setup symlink for sstate"
ln -s ~/sstate/rpi sstate-cache

# add the missing layers
echo "Adding layers"
bitbake-layers add-layer ../meta-openembedded/meta-oe/
bitbake-layers add-layer ../meta-openembedded/meta-multimedia/
bitbake-layers add-layer ../meta-openembedded/meta-python/
bitbake-layers add-layer ../meta-openembedded/meta-networking/
bitbake-layers add-layer ../meta-raspberrypi
bitbake-layers add-layer ../meta-wpe

# fix the configuration
echo "Creating auto.conf"

if [ -e $CONFFILE ]; then
    rm -rf $CONFFILE
fi
cat <<EOF > $CONFFILE
MACHINE = "${MACHINE}"
#IMAGE_FEATURES += "tools-debug"
#IMAGE_FEATURES += "tools-tweaks"
#IMAGE_FEATURES += "dbg-pkgs"
# explicitly disable x11 and enable opengl
GCCVERSION="6.%"
DISTRO_FEATURES_remove = "x11"
DISTRO_FEATURES_append = " opengl"
ENABLE_UART = "1"
HOSTTOOLS_NONFATAL_append = " ssh"
# initramfs bits for rpi builds
INITRAMFS_IMAGE_BUNDLE_rpi = "1"
INITRAMFS_IMAGE_rpi = "wpe-initramfs-image"
KERNEL_INITRAMFS = "-initramfs"
# Enlarge boot partition to 160MiB
BOOT_SPACE = "163840"
CONF_VERSION = "1"

EOF

# start build
echo "Starting build"
bitbake $BITBAKEIMAGE
