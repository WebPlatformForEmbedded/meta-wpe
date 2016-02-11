DESCRIPTION = "Metrological Middleware Packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-ml-middleware \
"

RDEPENDS_packagegroup-ml-middleware = "\
    cppsdk \
    webbridge \
"

RDEPENDS_packagegroup-ml-middleware_append_libc-glibc = "\
    dxdrm \
"
