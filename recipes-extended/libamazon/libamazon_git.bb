# TODO: Placeholder license 
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://${S}/../NOTICES.txt;md5=f5adad9a750c5dac1df560bc76ed0e34 \
"

SRC_URI = "git://git@github.com/Metrological/amazon.git;protocol=ssh;branch=development/thunder-ignition-backend"
SRCREV = "6e62383175da0c2d0a616975604ea93db7b02adb"
DEPENDS = "curl freetype icu libcap openssl libjpeg-turbo wpeframework"

PACKAGES = "${PN}"

AMAZON_BUILD_TYPE ?= "Debug"
AMAZON_DEVELOPMENT_MODE ?= "ON"
AMAZON_USE_DUMMY_DRM ?= "ON"
AMAZON_FAKE_PLAYER ?= "ON"

DEVICE_LAYER_DIR ?= "${S}/../thunder/linux-device-layer"
DISABLE_SAFE_BUILD_ROOT_CHECK ?= "ON"
BUILD_AS_SHARED_LIBRARY ?= "ON"

inherit pkgconfig cmake

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=${AMAZON_BUILD_TYPE} \
    -DBUILD_AS_SHARED_LIBRARY=${BUILD_AS_SHARED_LIBRARY} \
    -DCMAKE_INSTALL_PREFIX=${exec_prefix} \
    -DDEVICE_LAYER_DIR=${DEVICE_LAYER_DIR} \
    -DDEVICE_LAYER_CMAKE_ARGS='-DUSE_DUMMY_DRM=${AMAZON_USE_DUMMY_DRM}' \
    -DDEVELOPMENT_MODE=${AMAZON_DEVELOPMENT_MODE} \
    -DUSE_FAKE_PLAYER=${AMAZON_FAKE_PLAYER} \
    -DUSE_MEDIA_PIPELINE_BACKEND=OFF \
    -DDISABLE_SAFE_BUILD_ROOT_CHECK=${DISABLE_SAFE_BUILD_ROOT_CHECK} \
    -DIGNITION_PLATFORM_LINK_LIBRARIES=pthread\;cap"

do_install_append() {

    # The following location contains headers from 3rd party libraries.
    rm -rf "${D}/${includedir}/hawktracer/"
    rm -f "${D}/${includedir}/hawktracer.h"

    # Certificates are already provided by the openssl package.
    # rm -rf "${D}/${bindir}/certs"
    
    # Clean out empty directories.
    rm -rf "${D}/${exec_prefix}/persist" "${D}/${exec_prefix}/tmp"

    mkdir -p "${STAGING_INCDIR}/ignition/"
    cp -r "${DEVICE_LAYER_DIR}/interface/include/." "${STAGING_INCDIR}/ignition/"

    mkdir -p "${D}/${datadir}/ignition/"
    mv  "${D}/${exec_prefix}/manifest" \
        "${D}/${exec_prefix}/default_config.json" \
        "${D}/${exec_prefix}/shaders" \
        "${D}/${exec_prefix}/fonts" \
        "${D}/${exec_prefix}/images" \
        "${D}/${exec_prefix}/lua" "${D}/${datadir}/ignition/"
}

AMAZON_FILES = "\
                ${exec_prefix}/tests \
                ${bindir}/ignition \
                ${libdir}/libignition.so \
                ${libdir}/libprime-video-device-layer.so \
                ${libdir}/libamazon_backend_device.so \
                ${libdir}/libamazon_player.so \
                ${datadir}/ignition/ \
                "

FILES_${PN}     +=  "${AMAZON_FILES}"
Files_${PN}-dev += "\
                    ${AMAZON_FILES} \
                    ${libdir}/.debug \
                    ${bindir}/.debug \
                    "
