LICENSE = "CLOSED"

DEPENDS += " \
    bison-native gperf-native harfbuzz-native ninja-native ruby-native \
    cairo fontconfig freetype glib-2.0 gnutls harfbuzz icu jpeg pcre sqlite3 udev zlib \
    libinput libpng libsoup-2.4 libwebp libxml2 libxslt \
    virtual/egl virtual/libgles2 \
"

# Temp hack to satisfy dependency on libgdl.
# Machine specific dependencies should be handled via .bbappend's in the BSP layers.
DEPENDS_append_7401 = " intelce-display"

SRCREV = "5f0b6b16e74a60d86717789fd6fc54e6c91ac3e4"

SRC_URI = "git://github.com/Metrological/WebKitForWayland.git;protocol=http;branch=intelce"

S = "${WORKDIR}/git"

inherit cmake pkgconfig perlnative pythonnative

FULL_OPTIMIZATION_remove = "-g"
FULL_OPTIMIZATION_append = " -DNDEBUG"

EXTRA_OECMAKE += " \
  -DCMAKE_COLOR_MAKEFILE=OFF -DBUILD_SHARED_LIBS=ON -DPORT=WPE  \
  -G Ninja  \
  -DENABLE_ACCELERATED_2D_CANVAS=ON  \
  -DENABLE_BATTERY_STATUS=OFF  \
  -DENABLE_CANVAS_PATH=ON  \
  -DENABLE_CANVAS_PROXY=OFF  \
  -DENABLE_CHANNEL_MESSAGING=ON  \
  -DENABLE_CSP_NEXT=OFF  \
  -DENABLE_CSS3_TEXT=OFF  \
  -DENABLE_CSS3_TEXT_LINE_BREAK=OFF  \
  -DENABLE_CSS_BOX_DECORATION_BREAK=ON  \
  -DENABLE_CSS_COMPOSITING=OFF  \
  -DENABLE_CSS_DEVICE_ADAPTATION=OFF  \
  -DENABLE_CSS_GRID_LAYOUT=ON  \
  -DENABLE_CSS_IMAGE_ORIENTATION=OFF  \
  -DENABLE_CSS_IMAGE_RESOLUTION=OFF  \
  -DENABLE_CSS_IMAGE_SET=ON  \
  -DENABLE_CSS_REGIONS=ON  \
  -DENABLE_CSS_SHAPES=ON  \
  -DENABLE_CUSTOM_SCHEME_HANDLER=OFF  \
  -DENABLE_DATALIST_ELEMENT=OFF  \
  -DENABLE_DATA_TRANSFER_ITEMS=OFF  \
  -DENABLE_DETAILS_ELEMENT=ON  \
  -DENABLE_DEVICE_ORIENTATION=OFF  \
  -DENABLE_DOM4_EVENTS_CONSTRUCTOR=OFF  \
  -DENABLE_DOWNLOAD_ATTRIBUTE=OFF  \
  -DENABLE_ES6_CLASS_SYNTAX=OFF  \
  -DENABLE_FONT_LOAD_EVENTS=OFF  \
  -DENABLE_FTL_JIT=OFF  \
  -DENABLE_FTPDIR=OFF  \
  -DENABLE_FULLSCREEN_API=OFF  \
  -DENABLE_GAMEPAD=OFF  \
  -DENABLE_GEOLOCATION=OFF  \
  -DENABLE_ICONDATABASE=ON  \
  -DENABLE_INDEXED_DATABASE=OFF  \
  -DENABLE_INPUT_TYPE_COLOR=OFF  \
  -DENABLE_INPUT_TYPE_DATE=OFF  \
  -DENABLE_INPUT_TYPE_DATETIMELOCAL=OFF  \
  -DENABLE_INPUT_TYPE_DATETIME_INCOMPLETE=OFF  \
  -DENABLE_INPUT_TYPE_MONTH=OFF  \
  -DENABLE_INPUT_TYPE_TIME=OFF  \
  -DENABLE_INPUT_TYPE_WEEK=OFF  \
  -DENABLE_LEGACY_NOTIFICATIONS=OFF  \
  -DENABLE_LEGACY_VENDOR_PREFIXES=ON  \
  -DENABLE_LINK_PREFETCH=OFF  \
  -DENABLE_MATHML=OFF  \
  -DENABLE_MEDIA_CAPTURE=OFF  \
  -DENABLE_MEDIA_STATISTICS=OFF  \
  -DENABLE_METER_ELEMENT=ON  \
  -DENABLE_MHTML=OFF  \
  -DENABLE_MOUSE_CURSOR_SCALE=OFF  \
  -DENABLE_NAVIGATOR_CONTENT_UTILS=ON  \
  -DENABLE_NAVIGATOR_HWCONCURRENCY=ON  \
  -DENABLE_NETSCAPE_PLUGIN_API=OFF  \
  -DENABLE_NOSNIFF=OFF  \
  -DENABLE_NOTIFICATIONS=OFF  \
  -DENABLE_ORIENTATION_EVENTS=OFF  \
  -DENABLE_PERFORMANCE_TIMELINE=ON  \
  -DENABLE_PROXIMITY_EVENTS=OFF  \
  -DENABLE_QUOTA=OFF  \
  -DENABLE_REQUEST_ANIMATION_FRAME=ON  \
  -DENABLE_RESOLUTION_MEDIA_QUERY=OFF  \
  -DENABLE_RESOURCE_TIMING=ON  \
  -DENABLE_SECCOMP_FILTERS=OFF  \
  -DENABLE_STREAMS_API=ON  \
  -DENABLE_SUBTLE_CRYPTO=ON  \
  -DENABLE_SVG_FONTS=ON  \
  -DENABLE_TEMPLATE_ELEMENT=ON  \
  -DENABLE_TEXT_AUTOSIZING=OFF  \
  -DENABLE_TOUCH_EVENTS=ON  \
  -DENABLE_TOUCH_ICON_LOADING=OFF  \
  -DENABLE_TOUCH_SLIDER=OFF  \
  -DENABLE_USER_TIMING=ON  \
  -DENABLE_VIBRATION=OFF  \
  -DENABLE_WEBGL=ON  \
  -DENABLE_WEB_REPLAY=OFF  \
  -DENABLE_WEB_SOCKETS=ON  \
  -DENABLE_WEB_TIMING=ON  \
  -DENABLE_VIDEO=OFF  \
  -DENABLE_VIDEO_TRACK=OFF  \
  -DENABLE_WEB_AUDIO=OFF  \
  -DENABLE_XSLT=ON  \
  -DUSE_SYSTEM_MALLOC=OFF  \
  -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=ON  \
  -DENABLE_ENCRYPTED_MEDIA=OFF  \
  -DENABLE_DXDRM=OFF  \
  -DENABLE_PROVISIONING=ON  \
  -DENABLE_MEDIA_SOURCE=OFF  \
  -DENABLE_THREADED_COMPOSITOR=ON "

do_compile() {
   ${STAGING_BINDIR_NATIVE}/ninja ${BUILDDIR}
}

do_install() {
    install -d ${D}${includedir}/WPE
    install -m644 ${S}/Source/WebKit2/Shared/API/c/wpe/WebKit.h ${D}/${includedir}/WPE

    install -d ${D}${includedir}/WebKit
    cp -r ${S}/Source/WebKit2/Shared/API/c/* ${D}/${includedir}/WebKit
    cp -r ${S}/Source/WebKit2/Shared/API/c/wpe/* ${D}/${includedir}/WebKit
    cp -r ${S}/Source/WebKit2/UIProcess/API/C/* ${D}/${includedir}/WebKit
    cp -r ${S}/Source/WebKit2/UIProcess/API/C/wpe/* ${D}/${includedir}/WebKit

    install -d ${D}${libdir}
    cp -av ${B}/lib/libWPE.so* ${D}${libdir}/
    cp -av ${B}/lib/libWPEWebKit.so* ${D}${libdir}/

    # Hack: Remove the RPATH embedded in libWPEWebKit.so
    chrpath --delete ${D}${libdir}/libWPEWebKit.so

    install -d ${D}${bindir}
    install -m755 ${B}/bin/WPELauncher ${D}${bindir}/
    install -m755 ${B}/bin/WPENetworkProcess ${D}${bindir}/
    install -m755 ${B}/bin/WPEWebProcess ${D}${bindir}/

    # Hack: Remove RPATHs embedded in apps
    chrpath --delete ${D}${bindir}/WPELauncher
    chrpath --delete ${D}${bindir}/WPENetworkProcess
    chrpath --delete ${D}${bindir}/WPEWebProcess

    # Hack: Since libs were installed using 'cp', files will be owned by
    # the build user, which will trigger a QA warning. Fix them up manually.
    chown -R 0:0 ${D}${libdir}
}

LEAD_SONAME = "libWPEWebKit.so"

# libWPE.so isn't versioned, so force it into the runtime package.
# Also then over-ride the default FILES_SOLIBSDEV wildcard list so that only
# the remaining .so files (ie libWPEWebKit.so) end up in the -dev package.
FILES_${PN} += "${libdir}/libWPE.so"
FILES_SOLIBSDEV = "${libdir}/libWPEWebKit.so"

# Create separate packages for the binaries.
# Fixme, do we need to care about these at all?
PACKAGES =+ "${PN}-launcher-dbg ${PN}-launcher"
PACKAGES =+ "${PN}-networkprocess-dbg ${PN}-networkprocess"
PACKAGES =+ "${PN}-webprocess-dbg ${PN}-webprocess"

FILES_${PN}-launcher = "${bindir}/WPELauncher"
FILES_${PN}-launcher-dbg = "${bindir}/.debug/WPELauncher"
FILES_${PN}-networkprocess = "${bindir}/WPENetworkProcess"
FILES_${PN}-networkprocess-dbg = "${bindir}/.debug/WPENetworkProcess"
FILES_${PN}-webprocess = "${bindir}/WPEWebProcess"
FILES_${PN}-webprocess-dbg = "${bindir}/.debug/WPEWebProcess"
