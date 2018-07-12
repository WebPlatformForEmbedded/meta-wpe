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

## Usage

The image will start WPEFramework through the chosen init system, which will in turn start the WPEWebKit Browser and point it to a local index.html. You should see a Hello page with the IP of the device. There is no need to login to the device and start the browser manually.

WPEFramework loads several plugins, including a webserver and the wpewebkit browser. A locally supplied user interface is available to interact with the WPEFramework web services. All interactions with the browser and other plugins are available through web APIs. 

To interact with the browser:

1. Find the IP of the device on the welcome page
2. Navigate to the IP of the box with a browser to open the control UI
3. Open the WebKit Browser tab and set a new URL in the input field

All keys that are typed when the control page in focus are forwarded to the device. Alternatively you can also hookup USB remotes/keyboards.

### Change default URL

To make WPEFramework load another URL by default there are two ways:

* Set the startup URL at build time
* Modify the config on the device after you've build the image

#### Build time

To change the URL at build time, set the following variable in your `local.conf`:

```
WEBKITBROWSER_STARTURL = "http://www.github.com"
```

Rebuild your image as explained in the Quick start section. 


#### Runtime

Or change the local configuration:

```
$ vi /etc/WPEFramework/plugins/WebKitBrowser.json
```

In the JSON file, edit the URL key/value. Save and restart the box or reload WPEFramework through systemctl or sysinit.

To change what WPEFramework loads, such as turn on/off plugins or interact with the service through another way please see the [WPEFramework](https://github.com/WebPlatformForEmbedded/meta-wpe/wiki/WPEFramework) page on our wiki.

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
