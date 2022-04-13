SUMMARY = "Thunder packagegroup for WPEWebKit"
DESCRIPTION = "Web Platform for Embedded WebKit Packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-wpewebkit \
"

RDEPENDS:packagegroup-wpewebkit = "\
    wpewebkit \
    libwpe \
    wpewebkit-web-inspector-plugin \
"

# Additional OSS packages etc, which are only needed for WPE based images.
RDEPENDS:packagegroup-wpewebkit += "\
    fontconfig \
    fontconfig-utils \
    ttf-bitstream-vera \
"

