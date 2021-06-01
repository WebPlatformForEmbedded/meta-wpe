PACKAGECONFIG = "incapp inctest incplayer increndergl incsbprotocol xdgv5"
CXXFLAGS_append = " -DWESTEROS_PLATFORM_DRM"

do_configure_prepend() {
    sed -i -e 's/-lwesteros_simplebuffer_client/-lwesteros_compositor -lwesteros_simplebuffer_client/g' ${S}/drm/westeros-sink/Makefile.am
}

