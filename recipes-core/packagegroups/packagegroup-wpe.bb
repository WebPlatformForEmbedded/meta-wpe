DESCRIPTION = "Web Platform for Embedded Packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-wpe \
"

RDEPENDS_packagegroup-wpe = "\
    wpewebkit \
    wpebackend \
    wpewebkit-web-inspector-plugin \
    wpelauncher \
"

# Additional OSS packages etc, which are only needed for WPE based images.
RDEPENDS_packagegroup-wpe += "\
    fontconfig \
    fontconfig-utils \
    ttf-bitstream-vera \
"
