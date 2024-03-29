require gstreamer1.0-plugins.inc

LICENSE = "GPLv2+ & LGPLv2.1+"

DEPENDS_append = " gstreamer1.0-plugins-base libcap zlib bzip2"

inherit gettext

PACKAGECONFIG ??= " \
    ${GSTREAMER_ORC} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
    cairo flac gdk-pixbuf gudev jpeg id3demux icydemux libpng matroska soup speex taglib udp \
"
PACKAGECONFIG_append = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', 'v4l2', '', d)}"

X11DEPENDS = "virtual/libx11 libsm libxrender libxfixes libxdamage"

PACKAGECONFIG[cairo] = "--enable-cairo,--disable-cairo,cairo"
PACKAGECONFIG[dv1394] = "--enable-dv1394,--disable-dv1394,libiec61883 libavc1394 libraw1394"
PACKAGECONFIG[flac] = "--enable-flac,--disable-flac,flac"
PACKAGECONFIG[gdk-pixbuf] = "--enable-gdk_pixbuf,--disable-gdk_pixbuf,gdk-pixbuf"
PACKAGECONFIG[gudev] = "--with-gudev,--without-gudev,libgudev"
PACKAGECONFIG[id3demux] = "--enable-id3demux,--disable-id3demux,"
PACKAGECONFIG[icydemux] = "--enable-icydemux,--disable-icydemux,"
PACKAGECONFIG[jack] = "--enable-jack,--disable-jack,jack"
PACKAGECONFIG[jpeg] = "--enable-jpeg,--disable-jpeg,jpeg"
PACKAGECONFIG[libpng] = "--enable-libpng,--disable-libpng,libpng"
PACKAGECONFIG[libv4l2] = "--with-libv4l2,--without-libv4l2,v4l-utils"
PACKAGECONFIG[matroska] = "--enable-matroska,--disable-matroska,"
PACKAGECONFIG[pulseaudio] = "--enable-pulse,--disable-pulse,pulseaudio"
PACKAGECONFIG[soup] = "--enable-soup,--disable-soup,libsoup-2.4"
PACKAGECONFIG[speex] = "--enable-speex,--disable-speex,speex"
PACKAGECONFIG[taglib] = "--enable-taglib,--disable-taglib,taglib"
PACKAGECONFIG[udp] = "--enable-udp,--disable-udp"
PACKAGECONFIG[v4l2] = "--enable-gst_v4l2 --enable-v4l2-probe,--disable-gst_v4l2"
PACKAGECONFIG[vpx] = "--enable-vpx,--disable-vpx,libvpx"
PACKAGECONFIG[wavpack] = "--enable-wavpack,--disable-wavpack,wavpack"
PACKAGECONFIG[x11] = "--enable-x,--disable-x,${X11DEPENDS}"

EXTRA_OECONF += "\
    --enable-bz2 \
    --enable-oss \
    --enable-zlib \
    --disable-aalib \
    --disable-aalibtest \
    --disable-directsound \
    --disable-libcaca \
    --disable-libdv \
    --disable-oss4 \
    --disable-osx_audio \
    --disable-osx_video \
    --disable-shout2 \
    --disable-sunaudio \
    --disable-waveform \
"

FILES_${PN}-equalizer += "${datadir}/gstreamer-1.0/presets/*.prs"
