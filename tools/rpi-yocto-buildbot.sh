#!/bin/bash
# Bootstrapper for buildbot slave

DIR="build"
MACHINE="raspberrypi2"
CONFFILE="conf/local.conf"
BITBAKEIMAGE="westeros-wpe-image"

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
bitbake-layers add-layer ../meta-raspberrypi
bitbake-layers add-layer ../meta-wpe
bitbake-layers add-layer ../meta-openembedded/meta-oe/
bitbake-layers add-layer ../meta-openembedded/meta-multimedia/

# fix the configuration
echo "Creating local.conf"
mv $CONFFILE conf/local.conf.orig
echo "MACHINE = \"${MACHINE}\"" >> $CONFFILE
echo "DISTRO ?= \"poky\"" >> $CONFFILE
echo "IMAGE_FEATURES += \"tools-debug\"" >> $CONFFILE
echo "IMAGE_FEATURES += \"debug-tweaks\"" >> $CONFFILE
echo "#IMAGE_FEATURES += \"dbg-pkgs\"" >> $CONFFILE
echo "GCCVERSION=\"5.3%\"" >> $CONFFILE
echo "USER_CLASSES ?= \"buildstats image-mklibs image-prelink\"" >> $CONFFILE
echo "BB_DISKMON_DIRS = \"\
    STOPTASKS,${TMPDIR},1G,100K \
    STOPTASKS,${DL_DIR},1G,100K \
    STOPTASKS,${SSTATE_DIR},1G,100K \
    STOPTASKS,/tmp,100M,100K \
    ABORT,${TMPDIR},100M,1K \
    ABORT,${DL_DIR},100M,1K \
    ABORT,${SSTATE_DIR},100M,1K \
    ABORT,/tmp,10M,1K\" " >> $CONFFILE
echo "CONF_VERSION = \"1\"" >> $CONFFILE

# start build
echo "Starting build"
bitbake $BITBAKEIMAGE
