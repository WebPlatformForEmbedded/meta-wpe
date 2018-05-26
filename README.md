# meta-wpe

[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=55UJZHTXW8VTE)

`meta-wpe` is a meta layer for [OpenEmbedded / Yocto](https://www.yoctoproject.org/) build environments.
It provides the necessary recipes to build the [WebPlatformforEmbedded](https://wpewebkit.org/) components including the WPE [WebKit](https://webkit.org/) browser.

## Quick Links

* Git repository: <https://github.com/WebPlatformForEmbedded/meta-wpe>
* Issues management: <https://github.com/WebPlatformForEmbedded/meta-wpe/issues>
* Documentation: <https://github.com/WebPlatformForEmbedded/meta-wpe/wiki>

## Dependencies

This layer depends on:

    URI: git://git.yoctoproject.org/poky
    branch: master
    revision: HEAD

    URI: git://git.openembedded.org/meta-openembedded
    layers: meta-oe, meta-multimedia, meta-networking, meta-python
    branch: master
    revision: HEAD

And any additional BSP layer for your board, e.g. `meta-raspberrypi` or `meta-96boards`.

Check the documentation for more information on specific devices.

## Quick Start

1. Source `poky/oe-init-build-env wpe-build`.
2. Add this layer to `conf/bblayers.conf` and the dependencies above.
3. Set `MACHINE` in `conf/local.conf` to one of the supported boards.
4. Customise `conf/local.conf` according to your specific board and needs.
5. Build a Westeros-based image: `bitbake wpe-westeros-image` or build a EGLFS image: `bitbake wpe-eglfs-image`.
6. Copy the generated image file (in `tmp/deploy/images`) to an SD card.
7. Boot your board.

Please visit the [documentation](https://github.com/WebPlatformForEmbedded/meta-wpe/wiki) for detailed instructions, including tutorials and HowTo's for different devices.

## License

    Permission is hereby granted, free of charge, to any person obtaining a copy 
    of this software and associated documentation files (the "Software"), to deal 
    in the Software without restriction, including without limitation the rights 
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
    copies of the Software, and to permit persons to whom the Software is 
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in 
    all copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN 
    THE SOFTWARE.

