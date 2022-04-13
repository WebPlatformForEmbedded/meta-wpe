require cmake.inc

DEPENDS:append = " bzip2 curl expat libarchive ncurses xz zlib"

SRC_URI:append:class-nativesdk = " \
    file://OEToolchainConfig.cmake \
    file://environment.d-cmake.sh"

inherit cmake

# Strip ${prefix} from ${docdir}, set result into docdir_stripped
python () {
    prefix=d.getVar("prefix", True)
    docdir=d.getVar("docdir", True)

    if not docdir.startswith(prefix):
        bb.fatal('docdir must contain prefix as its prefix')

    docdir_stripped = docdir[len(prefix):]
    if len(docdir_stripped) > 0 and docdir_stripped[0] == '/':
        docdir_stripped = docdir_stripped[1:]

    d.setVar("docdir_stripped", docdir_stripped)
}

EXTRA_OECMAKE = "\
    -DCMAKE_DOC_DIR=${docdir_stripped}/cmake-${CMAKE_MAJOR_VERSION} \
    -DCMAKE_USE_SYSTEM_LIBRARIES=1 \
    -DCMAKE_USE_SYSTEM_LIBRARY_JSONCPP=0 \
    -DCMAKE_USE_SYSTEM_LIBRARY_LIBUV=0 \
    -DKWSYS_CHAR_IS_SIGNED=1 \
    -DBUILD_CursesDialog=0 \
    -DKWSYS_LFS_WORKS=1 \
"

do_install:append:class-nativesdk() {
    mkdir -p ${D}${datadir}/cmake
    install -m 644 ${WORKDIR}/OEToolchainConfig.cmake ${D}${datadir}/cmake/

    mkdir -p ${D}${SDKPATHNATIVE}/environment-setup.d
    install -m 644 ${WORKDIR}/environment.d-cmake.sh ${D}${SDKPATHNATIVE}/environment-setup.d/cmake.sh
}

FILES:${PN}:append:class-nativesdk = " ${SDKPATHNATIVE}"

FILES:${PN} += "${datadir}/cmake-${CMAKE_MAJOR_VERSION}"
FILES:${PN}-doc += "${docdir}/cmake-${CMAKE_MAJOR_VERSION}"

BBCLASSEXTEND = "nativesdk"

DEFAULT_PREFERENCE = "-1"

