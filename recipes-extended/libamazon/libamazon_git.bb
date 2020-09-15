# TODO: Placeholder license 
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://NOTICES.txt;md5=f5adad9a750c5dac1df560bc76ed0e34"

SRC_URI = "git://git@github.com/Metrological/amazon.git;protocol=ssh;branch=AVPKv2.1.0"
SRCREV = "cd3c0cf9cae45b4c017f2ca7cfc7734c6e628942"

S = "${WORKDIR}/git"
PR = "r0"
PACKAGES = "${PN}"

DEPENDS = "curl freetype icu libcap openssl libjpeg-turbo wpeframework"

# TODO: The tests install targets do not respect the toolchain install prefix.
# Test build artifacts end up being installed in hosts's /usr path, if the 
# following switches are not passed.
PACKAGECONFIG[amazon-cert-tests] = "-DCMAKE_INSTALL_PREFIX=${WORKDIR}/artifacts -DUNIT_TESTS_INSTALL_DIR=${WORKDIR}/artifacts, -DCMAKE_INSTALL_PREFIX=${exec_prefix},,"

inherit pkgconfig cmake

AMAZON_BUILD_TYPE ?= "Debug"
DEVICE_LAYER_DIR ?= "${S}/thunder/linux-device-layer"
DISABLE_SAFE_BUILD_ROOT_CHECK ?= "ON"
BUILD_AS_SHARED_LIBRARY ?= "OFF"

OECMAKE_SOURCEPATH = "${S}/ignition/"
EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=${AMAZON_BUILD_TYPE} \
    -DBUILD_AS_SHARED_LIBRARY=${BUILD_AS_SHARED_LIBRARY} \
    -DCMAKE_TOOLCHAIN_FILE=${WORKDIR}/toolchain.cmake \
    -DDEVICE_LAYER_DIR=${DEVICE_LAYER_DIR} \
    -DDEVICE_LAYER_CMAKE_ARGS='-DUSE_DUMMY_DRM=ON' \
    -DUSE_CCACHE=ON \
    -DDEVELOPMENT_MODE=ON \
    -DUSE_FAKE_PLAYER=ON \
    -DUSE_MEDIA_PIPELINE_BACKEND=OFF \
    -DDISABLE_SAFE_BUILD_ROOT_CHECK=${DISABLE_SAFE_BUILD_ROOT_CHECK} \
    -DIGNITION_PLATFORM_LINK_LIBRARIES=pthread\;cap \
    "

AMAZON_TARGETS = "${@bb.utils.contains('PACKAGECONFIG', 'amazon-cert-tests', 'test_ignition install-prime-video-device-layer-test integration-tests-package-with-mock-device-layer integration-tests-package', 'all', d)}"

do_compile() {
    oe_runmake ${AMAZON_TARGETS}
}

do_install() {

    if ${@bb.utils.contains('PACKAGECONFIG', 'amazon-cert-tests', 'true', 'false', d)}
    then
        mkdir -p "${D}/usr/"
        mv "${WORKDIR}"/artifacts/* "${D}/usr/"
    fi

    rm -rf "${D}/${exec_prefix}/tmp"
    rm -rf "${D}/${exec_prefix}/include"

    mkdir -p "${D}/${datadir}/ignition/" "${STAGING_INCDIR}/ignition/"
    mv  "${D}/${exec_prefix}/manifest" \
        "${D}/${exec_prefix}/default_config.json" \
        "${D}/${exec_prefix}/shaders" \
        "${D}/${exec_prefix}/fonts" \
        "${D}/${exec_prefix}/images" \
        "${D}/${exec_prefix}/assets" \
        "${D}/${exec_prefix}/lua" "${D}/${datadir}/ignition/"

    # Header files required for building the WPEPluginAmazon.
    cp -r "${DEVICE_LAYER_DIR}/interface/include/." "${STAGING_INCDIR}/ignition/"
}

AMAZON_FILES = "\
                ${bindir}/* \
                ${libdir}/* \
                ${datadir}/*/ \
                "

FILES_${PN}     +=  "${AMAZON_FILES}"
Files_${PN}-dev += "\
                    ${AMAZON_FILES} \
                    ${libdir}/.debug \
                    ${bindir}/.debug \
                    "
