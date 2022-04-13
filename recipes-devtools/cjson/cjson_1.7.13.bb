SUMMARY = "Ultralightweight JSON parser in ANSI C"
DESCRIPTION = "cJSON aims to be the dumbest possible parser that help to get job done with.\
    It's a single file of C, and a single header file."
AUTHOR = "Dave Gamble"
HOMEPAGE = "https://github.com/DaveGamble/cJSON"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=218947f77e8cb8e2fa02918dc41c50d0"

SRC_URI = "git://github.com/DaveGamble/cJSON.git;protocol=https;branch=master"
SRCREV = "39853e5148dad8dc5d32ea2b00943cf4a0c6f120"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE += "\
    -DENABLE_CJSON_UTILS=On \
    -DENABLE_CUSTOM_COMPILER_FLAGS=OFF \
    -DBUILD_SHARED_AND_STATIC_LIBS=On \
"

BBCLASSEXTEND = "native nativesdk"
FILES:${PN}-dev += "${libdir}/cmake"

