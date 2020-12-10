# TODO: Placeholder license 
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://NOTICES.txt;md5=f5adad9a750c5dac1df560bc76ed0e34"

SRC_URI = "git://git@github.com/Metrological/amazon.git;protocol=ssh;branch=AVPKv2.1.0"
SRCREV = "5d2b638b0f962e860a2e771ed64c4440d64fd285"

S = "${WORKDIR}/git"
PR = "r0"
PV = "git"
PACKAGES += " ${PN}-SOLIBSDEV"

DEPENDS = "curl openssl libcap wpeframework libamazon-backend libjpeg-turbo"

# TODO: The tests install targets do not respect the toolchain install prefix.
# Test build artifacts end up being installed in hosts's /usr path, 
# if the following switches are not passed.
PACKAGECONFIG[amazon-cert-tests] = "-DCMAKE_INSTALL_PREFIX=${WORKDIR}/artifacts, -DCMAKE_INSTALL_PREFIX=${exec_prefix},,"

AMAZON_USE_SYSTEM_LIBRARIES ?= "OFF"
AMAZON_BUILD_LIBJPEG ?= "OFF"
AMAZON_BUILD_LIBPNG ?= "ON"
AMAZON_BUILD_FREETYPE_LIB ?= "ON"
AMAZON_BUILD_HARFBUZZ_LIB ?= "ON"
AMAZON_IGNITE_DISABLE_ICU_BUILD ?= "OFF"

AMAZON_BUILD_TYPE ?= "Debug"
DEVICE_LAYER_DIR ?= "${S}/thunder/linux-device-layer/implementation/"
DISABLE_SAFE_BUILD_ROOT_CHECK ?= "ON"
BUILD_AS_SHARED_LIBRARY ?= "ON"

inherit pkgconfig cmake

OECMAKE_SOURCEPATH = "${S}/ignition/"
EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=${AMAZON_BUILD_TYPE} \
    -DBUILD_AS_SHARED_LIBRARY=${BUILD_AS_SHARED_LIBRARY} \
    -DBUILD_SHARED_LIBRARY_LAUNCHER=${BUILD_AS_SHARED_LIBRARY} \
    -DDEVICE_LAYER_DIR=${DEVICE_LAYER_DIR} \
    -DDEVICE_LAYER_CMAKE_ARGS='-DUSE_DUMMY_DRM=ON' \
    -DUSE_CCACHE=ON \
    -DDEVELOPMENT_MODE=ON \
    -DDISABLE_SAFE_BUILD_ROOT_CHECK=${DISABLE_SAFE_BUILD_ROOT_CHECK} \
    -DIGNITION_PLATFORM_LINK_LIBRARIES=pthread\;cap \
    -DUSE_FAKE_PLAYER=ON \
    -DENABLE_TESTS=OFF \
    -DUSE_SYSTEM_LIBRARIES=${AMAZON_USE_SYSTEM_LIBRARIES} \
    -DBUILD_HARFBUZZ_LIB=${AMAZON_BUILD_HARFBUZZ_LIB} \
    -DBUILD_LIBJPEG=${AMAZON_BUILD_LIBJPEG} \
    -DBUILD_LIBPNG=${AMAZON_BUILD_LIBPNG} \
    -DBUILD_FREETYPE_LIB=${AMAZON_BUILD_FREETYPE_LIB} \
    -DBUILD_HARFBUZZ_LIB=${AMAZON_BUILD_HARFBUZZ_LIB} \
    -DIGNITE_DISABLE_ICU_BUILD=${AMAZON_IGNITE_DISABLE_ICU_BUILD} \
    "

TEST_TARGETS = "install-prime-video-device-layer-test integration-tests-package"

EXTRA_OECMAKE_BUILD = "${@bb.utils.contains('PACKAGECONFIG', 'amazon-cert-tests', "${TEST_TARGETS}", 'all', d)}"

do_install_append() {

    if ${@bb.utils.contains('PACKAGECONFIG', 'amazon-cert-tests', 'true', 'false', d)}
    then
        mkdir -p "${D}/usr/"
        cp -fr "${WORKDIR}"/artifacts/* "${D}/usr/"
    fi

    rm -rf "${D}/${exec_prefix}/tmp"
    rm -rf "${D}/${exec_prefix}/include"
    rm -rf "${D}/${exec_prefix}/persist"
    rm -rf "${D}/${exec_prefix}/src"

    mkdir -p "${D}/${datadir}/ignition/" "${STAGING_INCDIR}/ignition/"

    # TODO:
    # Figure out, which cmake variable doubles the install path.
    # This results in two manifest files instead of one which was
    # appended during build time.
    # For now, manually add the required two lines into amazon's manifest.
    cat "${S}/ignition/assets/manifest" "${D}/${exec_prefix}/manifest" > "${D}/${exec_prefix}/manifest.temp"
    mv "${D}/${exec_prefix}/manifest.temp" "${D}/${exec_prefix}/manifest"

    mv  "${D}/${exec_prefix}/manifest" \
        "${D}/${exec_prefix}/default_config.json" \
        "${D}/${exec_prefix}/shaders" \
        "${D}/${exec_prefix}/fonts" \
        "${D}/${exec_prefix}/images" \
        "${D}/${exec_prefix}/lua" "${D}/${datadir}/ignition/"

    cp -r "${DEVICE_LAYER_DIR}/../interface/include/." "${STAGING_INCDIR}/ignition/"
}

AMAZON_FILES = "\
                ${bindir}/* \
                ${libdir}/* \
                ${datadir}/*/ \
                "

FILES_${PN}     += "${AMAZON_FILES}"
FILES_SOLIBSDEV += "${libdir}/.debug/* \
                    ${bindir}/.debug/* \
                    "
FILES_${PN}-dev = ""
