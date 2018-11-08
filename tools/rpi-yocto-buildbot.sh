#!/bin/bash
# Bootstrapper for buildbot slave
CONFFILE="conf/auto.conf"
MACHINE="raspberrypi3"
BRANCH="master"
BITBAKEIMAGE="virtual/kernel"

# make sure sstate is there
echo "Creating sstate directory"
mkdir -p ~/sstate/rpi/master

# make sure sstate is there
echo "Creating downloads directory"
mkdir -p ~/rpi/downloads

# fix permissions set by buildbot
echo "Fixing permissions for buildbot"
umask -S u=rwx,g=rx,o=rx

# bootstrap OE
echo "Initialize YoeDistro build Environment"

cd yoe-distro
export DOCKER_REPO="none"
export BASH_SOURCE="raspberrypi3-envsetup.sh"
. $MACHINE-envsetup.sh
yoe_setup

if [ ! -d "sources/meta-wpe" ]; then
	echo "Adding meta-wpe"
	yoe_add_layer git@github.com:WebPlatformForEmbedded/meta-wpe master
fi

# Symlink the cache
echo "Setup symlink for sstate"
ln -s ~/sstate/rpi/master build/sstate-cache
echo "Setup symlink for source downloads"
ln -s ~/rpi/downloads downloads

# fix the configuration
echo "Creating $CONFFILE"

if [ -e $CONFFILE ]; then
    rm -rf $CONFFILE
fi
cat <<EOF > $CONFFILE
#IMAGE_FEATURES += "tools-debug"
IMAGE_FEATURES += "debug-tweaks"
#IMAGE_FEATURES += "dbg-pkgs"
# rootfs for debugging
#IMAGE_GEN_DEBUGFS = "1"
#IMAGE_FSTYPES_DEBUGFS = "tar.gz"
# explicitly disable x11 and enable opengl
DISTRO_FEATURES_remove = "x11"
DISTRO_FEATURES_remove = "wayland"
#ENABLE_UART = "1"
SERIAL_CONSOLE = ""
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
echo "building ${BITBAKEIMAGE} for ${MACHINE} ..."
bitbake $BITBAKEIMAGE
