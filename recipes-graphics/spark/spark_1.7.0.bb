include spark.inc

DEPENDS_append = " curl freetype util-linux libjpeg-turbo libpng pxcore-libnode"

SRC_URI += "file://Spark.pc \
           file://0001-nanosvg-patches.patch \
           file://0002-pxScene-disable-2DMultisampleEXT.patch \
           file://0003-pxScene-init.js-path-configurable.patch \
           file://0004-spark-wpeframework-compositor.patch \
           file://0005-dukluv-git.patch \
           file://0006-dukluv-git.patch \
"

inherit cmake pkgconfig

PACKAGECONFIG ?= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland westeros', 'wpeframework', d)} \
"

PACKAGECONFIG[rtremote]     = "-DBUILD_RTCORE_LIBS=ON -DBUILD_RTCORE_STATIC_LIB=OFF,,,rtcore rtremote"
PACKAGECONFIG[wayland]      = "-DBUILD_WITH_WAYLAND=ON -DPXCORE_WAYLAND_EGL=ON -DBUILD_PXSCENE_WAYLAND_EGL=ON,,wayland"
PACKAGECONFIG[westeros]     = "-DBUILD_WITH_WESTEROS=ON -DPXCORE_WAYLAND_EGL=ON -DBUILD_PXSCENE_WAYLAND_EGL=ON,,westeros"
PACKAGECONFIG[wpeframework] = "-DBUILD_WITH_WPEFRAMEWORK=ON -DPXCORE_WPEFRAMEWORK=ON,,wpeframework"

COMPOSITOR ?= "${@bb.utils.contains('PACKAGECONFIG', 'wpeframework', 'wpeframework', 'wayland_egl', d)}"

EXTRA_OECMAKE += " \
    -DBUILD_WITH_TEXTURE_USAGE_MONITORING=ON \
    -DPXCORE_COMPILE_WARNINGS_AS_ERRORS=OFF \
    -DPXSCENE_COMPILE_WARNINGS_AS_ERRORS=OFF \
    -DCMAKE_SKIP_RPATH=ON \
    -DPXCORE_MATRIX_HELPERS=OFF \
    -DBUILD_PXWAYLAND_SHARED_LIB=OFF \
    -DBUILD_PXWAYLAND_STATIC_LIB=OFF \
    -DPREFER_SYSTEM_LIBRARIES=ON \
    -DDISABLE_TURBO_JPEG=ON \
    -DDISABLE_DEBUG_MODE=ON \
    -DSPARK_BACKGROUND_TEXTURE_CREATION=ON \
    -DSPARK_ENABLE_LRU_TEXTURE_EJECTION=OFF \
    -DSUPPORT_DUKTAPE=OFF \
    -DBUILD_DUKTAPE=ON \
    -DBUILD_PXSCENE_APP=OFF \
    -DBUILD_PXSCENE_STATIC_LIB=OFF \
    -DBUILD_PXSCENE_SHARED_LIB=ON \
    -DBUILD_PXSCENE_APP_WITH_PXSCENE_LIB=ON \
    -DBUILD_RTCORE_LIBS=ON \
    -DBUILD_RTCORE_STATIC_LIB=OFF \
"

#Set this flag based on preferred version requirement"
EXTRA_OECMAKE += "-DUSE_NODE_8=OFF"

TARGET_CXXFLAGS += " -fno-delete-null-pointer-checks "

do_install() {
    cp -ar ${S}/src/*.h ${STAGING_INCDIR}
    install -d ${STAGING_INCDIR}/unix
    cp -Rpf ${S}/src/unix/*.h ${STAGING_INCDIR}/unix

    install -d ${STAGING_INCDIR}/spark
    cp -ar ${S}/examples/pxScene2d/src/*.h ${STAGING_INCDIR}/spark

    install -d ${STAGING_INCDIR}/spark/${COMPOSITOR}
    cp -ar ${S}/src/${COMPOSITOR}/*.h ${STAGING_INCDIR}/spark/${COMPOSITOR}

    install -d ${PKG_CONFIG_DIR}
    install -m 644 ${WORKDIR}/Spark.pc ${PKG_CONFIG_DIR}/Spark.pc

    install -d ${D}${libdir}
    install -m 755 ${S}/examples/pxScene2d/src/libSpark.so ${D}${libdir}
    install -m 755 ${S}/build/wpe/librtCore.so ${D}${libdir}

    install -d ${D}${datadir}/WPEFramework/Spark
    cp -av --no-preserve=ownership ${S}/examples/pxScene2d/src/node_modules ${D}${datadir}/WPEFramework/Spark/
    install -m 755 ${S}/examples/pxScene2d/src/*.js ${D}${datadir}/WPEFramework/Spark/
    install -m 755 ${S}/examples/pxScene2d/src/*.json ${D}${datadir}/WPEFramework/Spark/
    install -m 755 ${S}/examples/pxScene2d/src/*.ttf ${D}${datadir}/WPEFramework/Spark/
    install -m 755 ${S}/examples/pxScene2d/src/sparkpermissions.conf ${D}${datadir}/WPEFramework/Spark/
    install -m 755 ${S}/examples/pxScene2d/src/waylandregistry.conf ${D}${datadir}/WPEFramework/Spark/
    cp -av --no-preserve=ownership ${S}/examples/pxScene2d/src/rcvrcore ${D}${datadir}/WPEFramework/Spark/
    cp -av --no-preserve=ownership ${S}/examples/pxScene2d/src/browser ${D}${datadir}/WPEFramework/Spark/
    cp -av --no-preserve=ownership ${S}/examples/pxScene2d/src/optimus ${D}${datadir}/WPEFramework/Spark/
    cp -av --no-preserve=ownership ${S}/examples/pxScene2d/src/duk_modules ${D}${datadir}/WPEFramework/Spark/
    cp -av --no-preserve=ownership ${S}/examples/pxScene2d/src/v8_modules ${D}${datadir}/WPEFramework/Spark/
    cp -av --no-preserve=ownership ${S}/examples/pxScene2d/src/rasterizer ${D}${datadir}/WPEFramework/Spark/
}

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so ${datadir}/WPEFramework/* ${PKG_CONFIG_DIR}/*.pc"

# ----------------------------------------------------------------------------

INSANE_SKIP_${PN} += "dev-so"
INSANE_SKIP_${PN}-dbg += "dev-so"
