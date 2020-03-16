FILESEXTRAPATHS_prepend := "${THISDIR}/pxcore-libnode:"

SRC_URI += "file://Spark.pc \
           file://pxScene-disable-2DMultisampleEXT.patch \
           file://spark-wpeframework-compositor.patch \
           file://pxScene-essos-support-for-shared-lib.patch \
"

PACKAGECONFIG ?= " wpeframework"

#    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland westeros', 'wpeframework', d)} \
#"

EXTRA_OECMAKE_remove        = " -DBUILD_WITH_WAYLAND=ON"
EXTRA_OECMAKE_remove        = " -DBUILD_WITH_WESTEROS=ON"
EXTRA_OECMAKE_remove        = " -DPXCORE_WAYLAND_EGL=ON"
EXTRA_OECMAKE_remove        = " -DBUILD_PXSCENE_WAYLAND_EGL=ON"
EXTRA_OECMAKE_remove        = " -DSPARK_BACKGROUND_TEXTURE_CREATION=ON"
EXTRA_OECMAKE_remove        = " -DSPARK_ENABLE_LRU_TEXTURE_EJECTION=ON"
EXTRA_OECMAKE_remove        = " -DBUILD_PXSCENE_ESSOS=ON"
EXTRA_OECMAKE_remove        = " -DPXCORE_ESSOS=ON"

PACKAGECONFIG[rtremote]     = "-DBUILD_RTCORE_LIBS=ON -DBUILD_RTCORE_STATIC_LIB=OFF,,,rtcore rtremote"
PACKAGECONFIG[wayland]      = "-DBUILD_WITH_WAYLAND=ON -DPXCORE_WAYLAND_EGL=ON -DBUILD_PXSCENE_WAYLAND_EGL=ON,,wayland"
PACKAGECONFIG[westeros]     = "-DBUILD_WITH_WESTEROS=ON -DPXCORE_WAYLAND_EGL=ON -DBUILD_PXSCENE_WAYLAND_EGL=ON,,westeros"
PACKAGECONFIG[wpeframework] = "-DBUILD_WITH_WPEFRAMEWORK=ON -DPXCORE_WPEFRAMEWORK=ON,,wpeframework"

COMPOSITOR          ?= "${@bb.utils.contains('PACKAGECONFIG', 'wpeframework', 'wpeframework', 'wayland_egl', d)}"
LIBRTCORE_SUBDIR    ?= "${@bb.utils.contains('PACKAGECONFIG', 'wpeframework', 'wpe', 'egl', d)}"

EXTRA_OECMAKE += " \
    -DBUILD_WITH_TEXTURE_USAGE_MONITORING=ON \
    -DPXCORE_MATRIX_HELPERS=OFF \
    -DBUILD_PXWAYLAND_SHARED_LIB=OFF \
    -DBUILD_PXWAYLAND_STATIC_LIB=OFF \
    -DPREFER_SYSTEM_LIBRARIES=ON \
    -DSPARK_BACKGROUND_TEXTURE_CREATION=OFF \
    -DSPARK_ENABLE_LRU_TEXTURE_EJECTION=OFF \
    -DSUPPORT_DUKTAPE=OFF \
    -DBUILD_DUKTAPE=ON \
    -DBUILD_PXSCENE_APP=OFF \
    -DBUILD_PXSCENE_STATIC_LIB=OFF \
    -DBUILD_PXSCENE_SHARED_LIB=ON \
    -DBUILD_PXSCENE_APP_WITH_PXSCENE_LIB=ON \
    -DBUILD_RTCORE_LIBS=ON \
    ${NODE_FLAG} \
"
TARGET_CXXFLAGS += " -fno-delete-null-pointer-checks "

do_install_append() {
    cp -ar ${S}/src/*.h ${STAGING_INCDIR}
    install -d ${STAGING_INCDIR}/unix
    cp -Rpf ${S}/src/unix/*.h ${STAGING_INCDIR}/unix

    install -d ${STAGING_INCDIR}/spark
    cp -ar ${S}/examples/pxScene2d/src/*.h ${STAGING_INCDIR}/spark

    install -d ${STAGING_INCDIR}/spark/${COMPOSITOR}
    cp -ar ${S}/src/${COMPOSITOR}/*.h ${STAGING_INCDIR}/spark/${COMPOSITOR}

    install -d ${PKG_CONFIG_DIR}
    install -m 644 ${WORKDIR}/Spark.pc ${PKG_CONFIG_DIR}/Spark.pc

    if [ -f ${STAGING_DIR_TARGET}/usr/lib/ibSpark.so ]
    then 
        rm -rf ${STAGING_DIR_TARGET}/usr/lib/ibSpark.so
    fi
    install -d ${D}${libdir}
    install -m 755 ${S}/examples/pxScene2d/src/libSpark.so ${D}${libdir}
    
    if [ -f ${STAGING_DIR_TARGET}/usr/lib/librtCore.so ]
    then
        rm -rf ${STAGING_DIR_TARGET}/usr/lib/librtCore.so
    fi

    install -m 755 ${S}/build/${LIBRTCORE_SUBDIR}/librtCore.so ${D}${libdir}
    if [ -d ${D}${datadir}/WPEFramework/Spark ]
    then
        rm -rf ${D}${datadir}/WPEFramework/Spark/*
    else
        install -d ${D}${datadir}/WPEFramework/Spark
    fi
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
