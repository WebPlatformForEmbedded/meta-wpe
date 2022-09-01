FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI_remove = "file://enforce-use-stl.patch"
SRC_URI_append = " file://tinyxml.pc "

LDFLAGS_append = " tinystr.o"

do_compile_prepend() {
    ${CXX} ${CXXFLAGS} ${EXTRA_CXXFLAGS} -c -o tinystr.o tinystr.cpp
}

do_install_append() {
    install -m 0644 ${S}/tinystr.h ${D}${includedir}
    install -Dm 644 ${B}/../tinyxml.pc ${D}${libdir}/pkgconfig/tinyxml.pc
}
