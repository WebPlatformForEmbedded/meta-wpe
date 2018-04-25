SUMMARY = "Netflix native application"
HOMEPAGE = "http://www.netflix.com/"
LICENSE = "CLOSED"

DEPENDS = "c-ares curl expat freetype graphite2 harfbuzz icu jpeg libmng libpng libwebp openssl"

SRCREV = "9a3fd8196fa91ab362032e2c65e0dfc868ee41bb"
PV = "4.2.3+git${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/netflix.git;protocol=ssh;branch=master \
           file://curlutils-stdint-include.patch \
           file://netflix.pc \
"

CCACHE = "${STAGING_DIR_NATIVE}${bindir}/ccache "

S = "${WORKDIR}/git"

inherit cmake pkgconfig pythonnative

NETFLIX_BACKEND ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wpeframework', 'rpi', d)}"

PACKAGECONFIG ?= "playready provisioning ${NETFLIX_BACKEND} virtualinput"

PACKAGECONFIG[default]          = "-DGIBBON_GRAPHICS=null \
                                   -DGIBBON_PLATFORM=posix \
                                   -DDPI_IMPLEMENTATION=reference \
                                   -DDPI_REFERENCE_VIDEO_DECODER=openmax-il \
                                   -DDPI_REFERENCE_AUDIO_DECODER=ffmpeg \
                                   -DDPI_REFERENCE_AUDIO_RENDERER=openmax-il \
                                   -DDPI_REFERENCE_AUDIO_MIXER=none \
                                   ,,ffmpeg libomxil"

PACKAGECONFIG[egl]              = "-DGIBBON_GRAPHICS=gles2-egl \
                                   -DGIBBON_PLATFORM=posix \
                                   ,,virtual/libgles2 virtual/egl"

PACKAGECONFIG[gles]             = "-DGIBBON_GRAPHICS=gles2 \
                                   -DGIBBON_PLATFORM=posix \
                                   ,,virtual/libgles2 virtual/egl"

PACKAGECONFIG[intelce]          = "-DGIBBON_GRAPHICS=intelce \
                                   -DGIBBON_PLATFORM=posix \
                                   -DDPI_IMPLEMENTATION=gstreamer \
                                   ,,intelce-display gstreamer1.0 gstreamer1.0-plugins-base virtual/egl virtual/libgles2"

PACKAGECONFIG[nexus]            = "-DGIBBON_GRAPHICS=nexus \
                                   -DGIBBON_PLATFORM=posix \
                                   -DDPI_IMPLEMENTATION=gstreamer \
                                   ,,broadcom-refsw gstreamer1.0 gstreamer1.0-plugins-base virtual/egl virtual/libgles2"

PACKAGECONFIG[rpi]              = "-DGIBBON_GRAPHICS=rpi-egl \
                                   -DGIBBON_PLATFORM=rpi \
                                   -DDPI_IMPLEMENTATION=gstreamer \
                                   -DGST_VIDEO_RENDERING=gl \
                                   ,,gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-bad virtual/egl virtual/libgles2"

PACKAGECONFIG[wpeframework]     = "-DGIBBON_INPUT=wpeframework \
                                   -DGIBBON_GRAPHICS=wpeframework \
                                   -DDPI_IMPLEMENTATION=gstreamer \
                                   ,,gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-bad wpeframework-plugins"


# Generic switches
PACKAGECONFIG[DDPLUS]           = "-DDPI_REFERENCE_HAVE_DDPLUS=true,,,"
PACKAGECONFIG[virtualinput]     = "-DUSE_NETFLIX_VIRTUAL_KEYBOARD=1,,wpeframework"

# DRM
PACKAGECONFIG[playready]        = "-DDPI_REFERENCE_DRM=playready,-DDPI_REFERENCE_DRM=none,playready,playready"
PACKAGECONFIG[provisioning]     = "-DNETFLIX_USE_PROVISION=ON,-DNETFLIX_USE_PROVISION=OFF,wpeframework,"

OECMAKE_SOURCEPATH = "${S}/netflix"

EXTRA_OECMAKE += " \
    -DBUILD_DPI_DIRECTORY=${S}/partner/dpi \
    -DCMAKE_BUILD_TYPE=Release \
    -DCMAKE_INSTALL_PREFIX=${D}/release \
    -DBUILD_COMPILE_RESOURCES=ON \
    -DBUILD_DEBUG=OFF \
    -DBUILD_PRODUCTION=ON \
    -DBUILD_SYMBOLS=OFF \
    -DBUILD_SHARED_LIBS=OFF \
    -DGIBBON_SCRIPT_JSC_DYNAMIC=OFF \
    -DGIBBON_SCRIPT_JSC_DEBUG=OFF \
    -DGIBBON_INPUT=devinput \
    -DNRDP_TOOLS=manufSSgenerator \
    -DGIBBON_MODE=shared \
"

export TARGET_CROSS = "$(GNU_TARGET_NAME)-"

CFLAGS += "-fPIC -DUSE_PLAYBIN=1"
CXXFLAGS += "-fPIC -DUSE_PLAYBIN=1"

PARALLEL_MAKE = ""

do_configure_prepend() {
    mkdir -p ${S}/netflix/src/platform/gibbon/data/etc/conf
    cp -f ${S}/netflix/resources/configuration/common.xml ${S}/netflix/src/platform/gibbon/data/etc/conf/common.xml
    cp -f ${S}/netflix/resources/configuration/config.xml ${S}/netflix/src/platform/gibbon/data/etc/conf/config.xml

    # to support $STAGING_DIR references in the gstreamer cmake list
    export STAGING_DIR="${STAGING_DIR_HOST}"
}

do_install() {
    # Temporary install to CMAKE_INSTALL_PREFIX ( ie ${D}/release )
    make -C ${B} install

    # Move files into expected locations + copy additional headers from ${S} and ${B}
    install -d ${D}${includedir}/netflix
    cp -av --no-preserve=ownership ${D}/release/include/* ${D}${includedir}/netflix/
    cp -av --no-preserve=ownership ${B}/include/nrdbase/config.h ${D}${includedir}/netflix/nrdbase/
    cp -av --no-preserve=ownership ${S}/netflix/src/platform/gibbon/*.h ${D}${includedir}/netflix/
    cp -av --no-preserve=ownership ${S}/netflix/src/platform/gibbon/bridge/*.h ${D}${includedir}/netflix/

    install -d ${D}${includedir}/netflix/gibbon
    cp -av --no-preserve=ownership ${B}/src/platform/gibbon/include/gibbon/*.h ${D}${includedir}/netflix/gibbon/

    install -d ${D}${datadir}/fonts/netflix
    cp -av --no-preserve=ownership ${B}/src/platform/gibbon/data/fonts/* ${D}${datadir}/fonts/netflix/
    install -m 0644 ${S}/netflix/src/platform/gibbon/resources/gibbon/fonts/LastResort.ttf ${D}${datadir}/fonts/netflix/

    install -d ${D}${libdir}
    install -m 0755 ${B}/src/platform/gibbon/libnetflix.so ${D}${libdir}/

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/netflix.pc ${D}${libdir}/pkgconfig/

    # Cleanup the temporary install
    rm -rf ${D}/release
}

# libnetflix.so isn't versioned, so we need to force .so files into the
# run-time package (and keep them out of the -dev package).

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

FILES_${PN} += "${datadir}/fonts"

INSANE_SKIP_${PN} += "already-stripped"
