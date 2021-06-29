require cmake.inc

DEPENDS_append = " bzip2-replacement-native expat-native xz-native zlib-native curl-native"

SRC_URI_append = " \
    file://cmlibarchive-disable-ext2fs.patch \
"

inherit native

B = "${WORKDIR}/build"
do_configure[cleandirs] = "${B}"

PACKAGECONFIG[ncurser] = "-DBUILD_CursesDialog=1,-DBUILD_CursesDialog=0,ncurses-native"

CMAKE_EXTRACONF = "\
    -DCMAKE_LIBRARY_PATH=${STAGING_LIBDIR_NATIVE} \
    -DCMAKE_USE_SYSTEM_LIBRARIES=1 \
    -DCMAKE_USE_SYSTEM_LIBRARY_JSONCPP=0 \
    -DCMAKE_USE_SYSTEM_LIBRARY_LIBARCHIVE=0 \
    -DCMAKE_USE_SYSTEM_LIBRARY_LIBUV=0 \
    -DENABLE_ACL=0 -DHAVE_ACL_LIBACL_H=0 \
    -DHAVE_SYS_ACL_H=0 \
"

do_configure () {
    ${S}/configure --verbose --prefix=${prefix} -- ${CMAKE_EXTRACONF}
}

do_compile() {
    oe_runmake
}

do_install() {
    oe_runmake 'DESTDIR=${D}' install
}

do_compile[progress] = "percent"
DEFAULT_PREFERENCE = "-1"

