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

RDEPENDS_packagegroup-wpewebkit_rpi = "wpebackend-rdk"

# Additional OSS packages etc, which are only needed for WPE based images.
RDEPENDS_packagegroup-wpewebkit += "\
    fontconfig \
    fontconfig-utils \
    ttf-bitstream-vera \
"
