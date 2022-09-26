include pxcore.inc

DEPENDS_append = " \
    curl freetype util-linux libpng pxcore-libnode giflib sqlite3 libjpeg-turbo \
    wpeframework-tools-native wpeframework wpeframework-clientlibraries \
"

SRC_URI += " \
    file://Spark.pc \
    file://0001-nanosvg-patches.patch \
    file://0002-pxScene-disable-2DMultisampleEXT.patch \
    file://0003-spark-wpeframework-compositor.patch \
    file://0004-dukluv-git.patch \
    file://0005-pxScene-essos-support-for-shared-lib.patch \
    file://0006-pxScene-initApp-load-issue-fix.patch \
    file://0007-node-v10.15.3_mods.patch \
"

inherit cmake pkgconfig

PACKAGECONFIG ?= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland westeros', 'wpeframework', d)} \
    disableturbojpeg \
"

PACKAGECONFIG[rtremote] = "-DBUILD_RTCORE_LIBS=ON -DBUILD_RTCORE_STATIC_LIB=OFF,,,rtcore rtremote"
PACKAGECONFIG[wayland] = "-DBUILD_WITH_WAYLAND=ON -DPXCORE_WAYLAND_EGL=ON -DBUILD_PXSCENE_WAYLAND_EGL=ON,,wayland"
PACKAGECONFIG[westeros] = "-DBUILD_WITH_WESTEROS=ON -DPXCORE_WAYLAND_EGL=ON -DBUILD_PXSCENE_WAYLAND_EGL=ON,,westeros"
PACKAGECONFIG[wpeframework] = "-DBUILD_WITH_WPEFRAMEWORK=ON -DPXCORE_WPEFRAMEWORK=ON,,wpeframework"
PACKAGECONFIG[disableturbojpeg] = "-DDISABLE_TURBO_JPEG=ON,,"
PACKAGECONFIG[pxwaylandsharedlib] = "-DBUILD_PXWAYLAND_SHARED_LIB=ON,-DBUILD_PXWAYLAND_SHARED_LIB=OFF,"
PACKAGECONFIG[pxwaylandstaticlib] = "-DBUILD_PXWAYLAND_STATIC_LIB=ON,-DBUILD_PXWAYLAND_STATIC_LIB=OFF,"

COMPOSITOR ?= "${@bb.utils.contains('PACKAGECONFIG', 'wpeframework', 'wpeframework', 'wayland_egl', d)}"
LIBRTCORE_SUBDIR ?= "${@bb.utils.contains('PACKAGECONFIG', 'wpeframework', 'wpe', 'egl', d)}"

EXTRA_OECMAKE += " \
    -DBUILD_WITH_TEXTURE_USAGE_MONITORING=ON \
    -DPXCORE_COMPILE_WARNINGS_AS_ERRORS=OFF \
    -DPXSCENE_COMPILE_WARNINGS_AS_ERRORS=OFF \
    -DCMAKE_SKIP_RPATH=ON \
    -DPXCORE_MATRIX_HELPERS=OFF \
    -DPREFER_SYSTEM_LIBRARIES=ON \
    -DDISABLE_DEBUG_MODE=ON \
    -DSPARK_BACKGROUND_TEXTURE_CREATION=OFF \
    -DSPARK_ENABLE_LRU_TEXTURE_EJECTION=OFF \
    -DSUPPORT_DUKTAPE=OFF \
    -DBUILD_DUKTAPE=ON \
    -DBUILD_PXSCENE_APP=OFF \
    -DBUILD_PXSCENE_STATIC_LIB=OFF \
    -DBUILD_PXSCENE_SHARED_LIB=ON \
    -DBUILD_PXSCENE_APP_WITH_PXSCENE_LIB=ON \
    -DBUILD_RTCORE_LIBS=ON \
    -DBUILD_RTCORE_STATIC_LIB=OFF \
    -DSPARK_ENABLE_OPTIMIZED_UPDATE=OFF \
"
TARGET_CXXFLAGS += " -fno-delete-null-pointer-checks "

do_install() {
    install -d "${D}${includedir}"
    cp -ar "${S}/src"/*.h "${D}${includedir}"
    install -d "${D}${includedir}/unix"
    cp -Rpf "${S}/src/unix"/*.h "${D}${includedir}/unix"

    install -d "${D}${includedir}/spark"
    cp -ar "${S}/examples/pxScene2d/src"/*.h "${D}${includedir}/spark"

    install -d "${D}${includedir}/spark/${COMPOSITOR}"
    cp -ar "${S}/src/${COMPOSITOR}"/*.h "${D}${includedir}/spark/${COMPOSITOR}"

    install -d "${D}${libdir}"/pkgconfig
    install -m 644 "${WORKDIR}"/Spark.pc "${D}${libdir}/pkgconfig"/Spark.pc

    install -d ${D}${libdir}
    install -m 755 "${S}/examples/pxScene2d/src"/libSpark.so "${D}${libdir}"
    install -m 755 "${S}/build/${LIBRTCORE_SUBDIR}"/librtCore.so "${D}${libdir}"

    install -d ${D}${datadir}/WPEFramework/Spark
    cp -av --no-preserve=ownership "${S}/examples/pxScene2d/src/node_modules" "${D}${datadir}/WPEFramework/Spark"
    install -m 755 "${S}/examples/pxScene2d/src"/*.js "${D}${datadir}/WPEFramework/Spark"
    install -m 755 "${S}/examples/pxScene2d/src"/*.json "${D}${datadir}/WPEFramework/Spark"
    install -m 755 "${S}/examples/pxScene2d/src"/*.ttf "${D}${datadir}/WPEFramework/Spark"
    install -m 755 "${S}/examples/pxScene2d/src"/sparkpermissions.conf "${D}${datadir}/WPEFramework/Spark"
    install -m 755 "${S}/examples/pxScene2d/src"/waylandregistry.conf "${D}${datadir}/WPEFramework/Spark"
    cp -av --no-preserve=ownership "${S}/examples/pxScene2d/src/rcvrcore" "${D}${datadir}/WPEFramework/Spark"
    cp -av --no-preserve=ownership "${S}/examples/pxScene2d/src/browser" "${D}${datadir}/WPEFramework/Spark"
    cp -av --no-preserve=ownership "${S}/examples/pxScene2d/src/optimus" "${D}${datadir}/WPEFramework/Spark"
    cp -av --no-preserve=ownership "${S}/examples/pxScene2d/src/duk_modules" "${D}${datadir}/WPEFramework/Spark"
    cp -av --no-preserve=ownership "${S}/examples/pxScene2d/src/v8_modules" "${D}${datadir}/WPEFramework/Spark"
    cp -av --no-preserve=ownership "${S}/examples/pxScene2d/src/rasterizer" "${D}${datadir}/WPEFramework/Spark"
}

FILES_SOLIBSDEV = ""
SPARK_PACKAGE = "\
    ${libdir}/libSpark.so \
    ${libdir}/librtCore.so \
    ${datadir}/WPEFramework/* \
"
FILES_${PN} += "${libdir} ${datadir}/WPEFramework"
FILES_${PN}-dev += "${includedir} ${libdir}/pkgconfig/*.pc"

