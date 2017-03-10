DESCRIPTION = "Metrological WPE Packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-ml-wpe \
"

RDEPENDS_packagegroup-ml-wpe = "\
    wpewebkit \
    wpewebkit-platform-plugin \
    wpewebkit-web-inspector-plugin \
    wpelauncher \
    cppsdk \
    webbridge \
"

# Additional OSS packages etc, which are only needed for WPE based images.
RDEPENDS_packagegroup-ml-wpe += "\
    fontconfig \
    fontconfig-utils \
    ttf-bitstream-vera \
"
