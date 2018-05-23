DESCRIPTION = "Web Platform for Embedded WebKit Packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-wpewebkit \
"

RDEPENDS_packagegroup-wpewebkit = "\
    wpewebkit \
    wpebackend \
    wpewebkit-web-inspector-plugin \
"

# Additional OSS packages etc, which are only needed for WPE based images.
RDEPENDS_packagegroup-wpewebkit += "\
    fontconfig \
    fontconfig-utils \
    ttf-bitstream-vera \
"
