SUMMARY = "Netflix native application"
HOMEPAGE = "http://www.netflix.com/"
LICENSE = "CLOSED"

DEPENDS = "freetype icu jpeg libpng libmng libwebp harfbuzz expat openssl c-ares curl graphite2"

SRCREV = "c23f8bcb37154d9b765e81f9e4ff339b71c62044"
PV = "4.2.2+git${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/netflix.git;protocol=ssh;branch=master"
SRC_URI += "file://curlutils-stdint-include.patch"
S = "${WORKDIR}/git"

inherit cmake pkgconfig pythonnative

export TARGET_CROSS="$(GNU_TARGET_NAME)-"

# configure the netflix backend for rendering
# the following variable (NETFLIX_BACKEND) needs to be set in the machine config
NETFLIX_BACKEND ?= "default"
NETFLIX_BACKEND_nexus = "nexus"
NETFLIX_BACKEND_egl = "egl"
NETFLIX_BACKEND_gles = "gles"
NETFLIX_BACKEND_rpi = "rpi"
NETFLIX_BACKEND_7401 = "intelce"

PACKAGECONFIG ?= "${NETFLIX_BACKEND}"

PACKAGECONFIG[rpi] = "-DGIBBON_GRAPHICS=rpi-egl -DGIBBON_PLATFORM=rpi -DDPI_IMPLEMENTATION=gstreamer,,userland gstreamer1.0 gstreamer1.0-plugins-base virtual/egl virtual/libgles2"
PACKAGECONFIG[nexus] = "-DGIBBON_GRAPHICS=nexus -DGIBBON_PLATFORM=posix -DDPI_IMPLEMENTATION=gstreamer,,bcm-refsw gstreamer1.0 virtual/egl virtual/libgles2"
PACKAGECONFIG[intelce] = "-DGIBBON_GRAPHICS=intelce -DGIBBON_PLATFORM=posix -DDPI_IMPLEMENTATION=gstreamer,,intelce-display gstreamer1.0-fsmd virtual/egl virtual/libgles2"
PACKAGECONFIG[egl] = "-DGIBBON_GRAPHICS=gles2-egl -DGIBBON_PLATFORM=posix,,virtual/libgles2 virtual/egl"
PACKAGECONFIG[gles] = "-DGIBBON_GRAPHICS=gles2 -DGIBBON_PLATFORM=posix,,virtual/libgles2 virtual/egl"
PACKAGECONFIG[default] = "-DGIBBON_GRAPHICS=null \
			  -DGIBBON_PLATFORM=posix \
			  -DDPI_IMPLEMENTATION=reference \
			   -DDPI_REFERENCE_VIDEO_DECODER=openmax-il \
                          -DDPI_REFERENCE_AUDIO_DECODER=ffmpeg \
			   -DDPI_REFERENCE_AUDIO_RENDERER=openmax-il \
                          -DDPI_REFERENCE_AUDIO_MIXER=none \
                          ,,ffmpeg libomxil"


# DRM
NETFLIX_DRM ?= ""
NETFLIX_DRM_playready = "playready"

PACKAGECONFIG ?= "${NETFLIX_DRM}"

PACKAGECONFIG[playready] = "-DDPI_REFERENCE_DRM=playready,-DDPI_REFERENCE_DRM=none,playready"

OECMAKE_SOURCEPATH = "${S}/netflix"

EXTRA_OECMAKE += " \
	-DBUILD_DPI_DIRECTORY=${S}/partner/dpi \
	-DCMAKE_BUILD_TYPE=Release \
	-DCMAKE_INSTALL_PREFIX=/netflix \
	-DBUILD_COMPILE_RESOURCES=1 \
	-DBUILD_QA=0 \
	-DBUILD_SHARED_LIBS=0 \
	-DGIBBON_MODE=executable \
	-DGIBBON_SCRIPT_JSC_DYNAMIC=1 \
	-DGIBBON_SCRIPT_JSC_DEBUG=0 \
	-DGIBBON_INPUT=devinput \
	-DNRDP_HAS_IPV6=0 \
	-DNRDP_HAS_TRACING=0 \
	-DNRDP_HAS_TEST_INSTRUMENTATION=0 \
	-DNRDP_HAS_ON_INSTRUMENTATION=0 \
	-DNRDP_HAS_DEBUG_INSTRUMENTATION=0 \
	-DNRDP_HAS_SWITCHED_INSTRUMENTATION=0 \
	-DNRDP_HAS_INSTRUMENTATION=0 \
	-DNRDP_CRASH_REPORTING="off" \
	-DNRDP_TOOLS=manufSSgenerator \
"

do_configure_prepend() {
	mkdir -p ${S}/netflix/src/platform/gibbon/data/etc/conf
	cp -f ${S}/netflix/resources/configuration/common.xml ${S}/netflix/src/platform/gibbon/data/etc/conf/common.xml
	cp -f ${S}/netflix/resources/configuration/config.xml ${S}/netflix/src/platform/gibbon/data/etc/conf/config.xml

	# to support $STAGING_DIR references in the gstreamer cmake list
	export STAGING_DIR="${STAGING_DIR_HOST}"
}

do_install() {
	install -D -m 0755 ${B}/src/platform/gibbon/libJavaScriptCore.so ${D}${libdir}/libJavaScriptCore.so
	install -D -m 0755 ${B}/src/platform/gibbon/netflix ${D}${bindir}/netflix
	install -d ${D}${datadir}/fonts/netflix
	cp -av ${B}/src/platform/gibbon/data/fonts/* ${D}${datadir}/fonts/netflix/

	install -D -m 0644 ${S}/netflix/src/platform/gibbon/resources/gibbon/fonts/LastResort.ttf ${D}${datadir}/fonts/netflix/LastResort.ttf

	# same hack for the fonts
	chown -R 0:0 ${D}${datadir}
	# remove RPATH from binary
	chrpath --delete ${D}${bindir}/netflix
}
FILES_${PN} = "${bindir}/netflix ${libdir}/libJavaScriptCore.so \
               ${datadir}/*"

FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} += "dev-so already-stripped"

PARALLEL_MAKE = ""

