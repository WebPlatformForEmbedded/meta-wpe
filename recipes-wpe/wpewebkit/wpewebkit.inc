SUMMARY = "WPE WebKit - port for embedded platforms"
DESCRIPTION = "WPE WebKit port pairs the WebKit engine with OpenGL-ES (OpenGL for Embedded Systems), \
           allowing embedders to create simple and performant systems based on Web platform technologies. \
           It is designed with hardware acceleration in mind, relying on EGL, and OpenGL ES."
HOMEPAGE = "https://wpewebkit.org/"
BUGTRACKER = "https://bugs.webkit.org/"
LICENSE = "BSD & LGPLv2+"
LIC_FILES_CHKSUM = "file://Source/WebCore/LICENSE-LGPL-2.1;md5=a778a33ef338abbaf8b8a7c36b6eec80"

# WPE WebKit Common
DEPENDS_append = " \
    libwpe \
    bison-native ccache-native glib-2.0-native gperf-native libxml2-native ninja-native ruby-native chrpath-replacement-native \
    cairo fontconfig freetype glib-2.0 gnutls harfbuzz icu jpeg pcre sqlite3 zlib \
    libepoxy libpng libsoup-2.4 libwebp libxml2 libxslt thunder\
    virtual/egl virtual/libgles2 \
"

# unset CCACHE_DISABLE for this recipe
CCACHE_DISABLE[unexport] = "1"

S = "${WORKDIR}/git"

inherit cmake pkgconfig perlnative
# FIXME: check whether python version is dependant on yocto version or webkit version
inherit ${@oe.utils.ifelse(d.getVar('DISTRO_CODENAME', True) == 'dunfell', 'python3native', 'pythonnative')}

inherit ${@'cmake_qt5' if 'qt5-layer' in d.getVar('BBFILE_COLLECTIONS', True).split() else ''}

WPE_PLATFORM ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'westeros westeros-sink', 'egl', d)}"
WPE_PLATFORM ?= "${@bb.utils.contains('virtual/egl', 'broadcom-refsw', 'nexus', 'egl', d)}"
WPE_PLATFORM_x86 ?= "intelce"
WPE_PLATFORM ?= "egl"

PACKAGECONFIG ?= "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 'opencdm', '', d)} \
    2dcanvas deviceorientation encryptedmedia fullscreenapi fetchapi gamepad indexeddb mediasource mediastatistics notifications nativevideo sampling-profiler subtitle subtlecrypto video webaudio webcrypto gst_gl remote-inspector openjpeg unified-builds service-worker \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '' ,d)} \
    thunder ${WPE_PLATFORM} \
"
PACKAGECONFIG_append_dunfell = " woff2"

# Mesa only offscreen target support for Westeros backend
# FIXME Needs to be moved to mesa backend
PACKAGECONFIG[westeros-mesa] = "-DUSE_WPEWEBKIT_BACKEND_WESTEROS_MESA=ON,,"

# WPE Platform specific switches
PACKAGECONFIG[intelce] = "-DUSE_WPEWEBKIT_PLATFORM_INTEL_CE=ON -DUSE_HOLE_PUNCH_GSTREAMER=ON,,"
PACKAGECONFIG[nexus] = "-DUSE_WPEWEBKIT_PLATFORM_BCM_NEXUS=ON -DUSE_HOLE_PUNCH_GSTREAMER=ON,,"
PACKAGECONFIG[qcomdb] = "-DUSE_WPEWEBKIT_PLATFORM_QCOM_DB=ON,,"
PACKAGECONFIG[egl] = "-DUSE_GSTREAMER_GL=ON,-DUSE_GSTREAMER_GL=OFF,gstreamer1.0-plugins-base gstreamer1.0-plugins-bad"
PACKAGECONFIG[westeros] = "-DUSE_WPEWEBKIT_PLATFORM_WESTEROS=ON,,westeros"

# westeros-sink, override with egl if glimagesink is vsink
PACKAGECONFIG[westeros-sink] = "-DUSE_HOLE_PUNCH_GSTREAMER=ON -DUSE_WESTEROS_SINK=ON -DUSE_GSTREAMER_HOLEPUNCH=ON,,westeros"
PACKAGECONFIG[wayland-sink] = "-DUSE_WAYLAND_SINK=ON,,gstreamer1.0-plugins-bad"

# WPE features
PACKAGECONFIG[2dcanvas] = "-DENABLE_ACCELERATED_2D_CANVAS=ON,-DENABLE_ACCELERATED_2D_CANVAS=OFF,"
PACKAGECONFIG[accessibility] = "-DENABLE_ACCESSIBILITY=ON,-DENABLE_ACCESSIBILITY=OFF,atk at-spi2-atk"
PACKAGECONFIG[bubblewrap] = "-DENABLE_BUBBLEWRAP_SANDBOX=ON,-DENABLE_BUBBLEWRAP_SANDBOX=OFF,bubblewrap xdg-dbus-proxy bubblewrap-native xdg-dbus-proxy-native libseccomp"
PACKAGECONFIG[developer-mode] = "-DDEVELOPER_MODE=ON,-DDEVELOPER_MODE=OFF,wayland-native wayland-protocols wpebackend-fdo"
PACKAGECONFIG[deviceorientation] = "-DENABLE_DEVICE_ORIENTATION=ON,-DENABLE_DEVICE_ORIENTATION=OFF,"
PACKAGECONFIG[encryptedmedia] = "-DENABLE_ENCRYPTED_MEDIA=ON,-DENABLE_ENCRYPTED_MEDIA=OFF,libgcrypt"
PACKAGECONFIG[fetchapi] = "-DENABLE_FETCH_API=ON,-DENABLE_FETCH_API=OFF,"
PACKAGECONFIG[fullscreenapi] = "-DENABLE_FULLSCREEN_API=ON,-DENABLE_FULLSCREEN_API=OFF,"
PACKAGECONFIG[fusion] = "-DUSE_FUSION_API_GSTREAMER=ON,-DUSE_FUSION_API_GSTREAMER=OFF,"
PACKAGECONFIG[gamepad] = "-DENABLE_GAMEPAD=ON,-DENABLE_GAMEPAD=OFF,"
PACKAGECONFIG[geolocation] = "-DENABLE_GEOLOCATION=ON,-DENABLE_GEOLOCATION=OFF,geoclue"
PACKAGECONFIG[gst_gl] = "-DUSE_GSTREAMER_GL=ON,-DUSE_GSTREAMER_GL=OFF,gstreamer1.0-plugins-base,${GL_DEPENDENCY}"
PACKAGECONFIG[indexeddb] = "-DENABLE_INDEXED_DATABASE=ON,-DENABLE_INDEXED_DATABASE=OFF,"
PACKAGECONFIG[logs] = "-DENABLE_LOGS=ON,-DENABLE_LOGS=OFF,"
PACKAGECONFIG[mediasource] = "-DENABLE_MEDIA_SOURCE=ON,-DENABLE_MEDIA_SOURCE=OFF,gstreamer1.0 gstreamer1.0-plugins-good,${RDEPS_MEDIASOURCE}"
PACKAGECONFIG[mediastatistics] = "-DENABLE_MEDIA_STATISTICS=ON,-DENABLE_MEDIA_STATISTICS=OFF,"
PACKAGECONFIG[minibrowser] = "-DENABLE_MINIBROWSER=ON,-DENABLE_MINIBROWSER=OFF,"
PACKAGECONFIG[nativeaudio] = "-DENABLE_NATIVE_AUDIO=ON,-DENABLE_NATIVE_AUDIO=OFF,"
PACKAGECONFIG[nativevideo] = "-DENABLE_NATIVE_VIDEO=ON,-DENABLE_NATIVE_VIDEO=OFF,"
PACKAGECONFIG[notifications] = "-DENABLE_NOTIFICATIONS=ON,-DENABLE_NOTIFICATIONS=OFF,"
PACKAGECONFIG[openjpeg] = "-DUSE_OPENJPEG=ON,-DUSE_OPENJPEG=OFF,openjpeg"
PACKAGECONFIG[sampling-profiler] = "-DENABLE_SAMPLING_PROFILER=ON,-DENABLE_SAMPLING_PROFILER=OFF,"
PACKAGECONFIG[service-worker] = "-DENABLE_SERVICE_WORKER=ON,-DENABLE_SERVICE_WORKER=OFF,"
PACKAGECONFIG[subtlecrypto] = "-DENABLE_SUBTLE_CRYPTO=ON,-DENABLE_SUBTLE_CRYPTO=OFF,"
PACKAGECONFIG[subtitle] = "-DENABLE_TEXT_SINK=ON,-DENABLE_TEXT_SINK=OFF,"
PACKAGECONFIG[touch] = "-DENABLE_TOUCH_EVENTS=ON,,"
PACKAGECONFIG[udev] = "-DUSE_WPEWEBKIT_INPUT_UDEV=ON,,udev"
PACKAGECONFIG[video] = "-DENABLE_VIDEO=ON -DENABLE_VIDEO_TRACK=ON,-DENABLE_VIDEO=OFF -DENABLE_VIDEO_TRACK=OFF,gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad,${RDEPS_VIDEO}"
PACKAGECONFIG[webaudio] = "-DENABLE_WEB_AUDIO=ON,-DENABLE_WEB_AUDIO=OFF,gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good,${RDEPS_WEBAUDIO}"
PACKAGECONFIG[webgl2] = "-DENABLE_WEBGL2=ON,-DENABLE_WEBGL2=OFF,"
PACKAGECONFIG[remote-inspector] = "-DENABLE_REMOTE_INSPECTOR=ON,-DENABLE_REMOTE_INSPECTOR=OFF,"
PACKAGECONFIG[webcrypto] = "-DENABLE_WEB_CRYPTO=ON,-DENABLE_WEB_CRYPTO=OFF,libgcrypt libtasn1"
PACKAGECONFIG[webrtc] = "-DENABLE_WEB_RTC=ON,-DENABLE_WEB_RTC=OFF,libvpx libevent libopus"
PACKAGECONFIG[woff2] = "-DUSE_WOFF2=ON,-DUSE_WOFF2=OFF,woff2"
PACKAGECONFIG[qtwpe] = "-DENABLE_WPE_QT_API=ON,-DENABLE_WPE_QT_API=OFF,qtbase-native qtbase qtdeclarative qtquickcontrols2 libepoxy wpebackend-fdo"
PACKAGECONFIG[openjpeg] = "-DUSE_OPENJPEG=ON,-DUSE_OPENJPEG=OFF,openjpeg"
PACKAGECONFIG[unified-builds] = "-DENABLE_UNIFIED_BUILDS=ON,-DENABLE_UNIFIED_BUILDS=OFF,"
PACKAGECONFIG[systemd] = "-DUSE_SYSTEMD=ON,-DUSE_SYSTEMD=OFF,"
PACKAGECONFIG[thunder] = "-DENABLE_THUNDER=ON,-DENABLE_THUNDER=OFF,"
PACKAGECONFIG[video-plane-display-dmabuf] = "-DUSE_WPE_VIDEO_PLANE_DISPLAY_DMABUF=ON,-DUSE_WPE_VIDEO_PLANE_DISPLAY_DMABUF=OFF,wpebackend-fdo"
PACKAGECONFIG[webxr] = "-DENABLE_WEBXR=ON,-DENABLE_WEBXR=OFF,"
PACKAGECONFIG[webkit2capi] = "-DEXPORT_DEPRECATED_WEBKIT2_C_API=ON,,"

# DRM
PACKAGECONFIG[opencdm] = "-DENABLE_OPENCDM=ON,-DENABLE_OPENCDM=OFF,wpeframework-clientlibraries"
PACKAGECONFIG[playready] = "-DENABLE_PLAYREADY=ON,-DENABLE_PLAYREADY=OFF,playready"

PACKAGECONFIG[gst_httpsrc] = "-DUSE_GSTREAMER_WEBKIT_HTTP_SRC=ON,,"
PACKAGECONFIG[gst_holepunch] = "-DUSE_HOLE_PUNCH_GSTREAMER=ON -DUSE_GSTREAMER_HOLEPUNCH=ON,,"

EXTRA_OECMAKE += "\
    -DCMAKE_BUILD_TYPE=Release \
    -DPORT=WPE \
    -G Ninja \
    -DPYTHON_EXECUTABLE=${PYTHON} \
"

EXTRANATIVEPATH += "chrpath-native"

# don't build debug
FULL_OPTIMIZATION_remove = "-g"
FULL_OPTIMIZATION_append = " -DNDEBUG"

# JSC JIT on ARMv7 is better supported with Thumb2 instruction set.
ARM_INSTRUCTION_SET_armv7a = "thumb"
ARM_INSTRUCTION_SET_armv7r = "thumb"
ARM_INSTRUCTION_SET_armv7m = "thumb"
ARM_INSTRUCTION_SET_armv7ve = "thumb"

# If SSE code compiles, assume it runs successfully (it can't actually run
# because of cross compiling)
EXTRA_OECMAKE_append_x86 = " -DHAVE_SSE2_EXTENSIONS_EXITCODE=0"
# mips/gold does not yet implement
# error: .gnu.hash is incompatible with the MIPS ABI
EXTRA_OECMAKE_append_mipsarch = " -DUSE_LD_GOLD=OFF "

LDFLAGS_append_riscv64 = " -pthread"

FULL_OPTIMIZATION_remove = "-g"

LEAD_SONAME = "libWPEWebKit.so"
PACKAGES =+ "${PN}-web-inspector-plugin ${PN}-qtwpe-qml-plugin"
FILES_${PN} += "${libdir}/wpe-webkit*/injected-bundle/libWPEInjectedBundle.so"
FILES_${PN}-web-inspector-plugin += "${libdir}/libWPEWebInspectorResources.so ${libdir}/wpe-webkit-*/libWPEWebInspectorResources.so"
INSANE_SKIP_${PN}-web-inspector-plugin = "dev-so"

FILES_${PN}-qtwpe-qml-plugin += "${libdir}/qt5/qml/org/wpewebkit/qtwpe/*"
INSANE_SKIP_${PN}-qtwpe-qml-plugin = "dev-so"

GL_DEPENDENCY ?= "gstreamer1.0-plugins-base-opengl"
GL_DEPENDENCY_morty = "gstreamer1.0-plugins-bad-opengl"
GL_DEPENDENCY_dunfell = "gstreamer1.0-plugins-base-opengl"

MPG_DEPENDENCY ?= "gstreamer1.0-plugins-good-mpg123"
MPG_DEPENDENCY_morty = "gstreamer1.0-plugins-ugly-mpg123"
MPG_DEPENDENCY_dunfell = "gstreamer1.0-plugins-good-mpg123"

# Extra runtime depends
RDEPENDS_${PN} += "\
    virtual/wpebackend \
"

# The RDEPS_EXTRA plugins are all required for certain media playback use cases,
# but have not yet been classified as being specific dependencies for video,
# webaudio or mediasource. Until that classification is done, add them all to
# each of the three groups...
RDEPS_MEDIASOURCE ?= " \
    gstreamer1.0-plugins-good-isomp4 \
    ${RDEPS_EXTRA} \
"

RDEPS_VIDEO ?= " \
    gstreamer1.0-plugins-base-app \
    gstreamer1.0-plugins-base-playback \
    gstreamer1.0-plugins-base-audioconvert \
    gstreamer1.0-plugins-base-audiomixer \
    gstreamer1.0-plugins-base-audioresample \
    gstreamer1.0-plugins-base-gio \
    gstreamer1.0-plugins-base-opus \
    gstreamer1.0-plugins-base-videoconvert \
    gstreamer1.0-plugins-base-videoscale \
    gstreamer1.0-plugins-base-volume \
    gstreamer1.0-plugins-base-typefindfunctions \
    gstreamer1.0-plugins-good-audiofx \
    gstreamer1.0-plugins-good-audioparsers \
    gstreamer1.0-plugins-good-autodetect \
    gstreamer1.0-plugins-good-avi \
    gstreamer1.0-plugins-good-deinterlace \
    gstreamer1.0-plugins-good-interleave \
    gstreamer1.0-plugins-good-matroska \
    ${MPG_DEPENDENCY} \
    gstreamer1.0-plugins-bad-dashdemux \
    gstreamer1.0-plugins-bad-hls \
    gstreamer1.0-plugins-bad-mpegtsdemux \
    gstreamer1.0-plugins-bad-opusparse \
    gstreamer1.0-plugins-bad-smoothstreaming \
    gstreamer1.0-plugins-bad-videoparsersbad \
    ${RDEPS_EXTRA} \
"

RDEPS_WEBAUDIO ?= " \
    gstreamer1.0-plugins-good-wavparse \
    ${RDEPS_EXTRA} \
"

RDEPENDS_${PN} += "\
    virtual/libwpe \
    virtual/wpebackend \
"

# Extra runtime recommends
RRECOMMENDS_${PN} += "\
    ca-certificates \
    shared-mime-info \
    ttf-bitstream-vera \
    ${PN}-web-inspector-plugin \
    ${PN}-qtwpe-qml-plugin \
    ${@bb.utils.contains('PACKAGECONFIG', 'video', 'gstreamer1.0-plugins-base-meta gstreamer1.0-plugins-good-meta gstreamer1.0-plugins-bad-meta', '', d)} \
"

