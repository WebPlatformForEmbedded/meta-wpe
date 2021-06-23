# meta-wpe

[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=55UJZHTXW8VTE)

`meta-wpe` is a meta layer for [OpenEmbedded / Yocto](https://www.yoctoproject.org/) build environments.
It provides the necessary recipes to build the [WebPlatformforEmbedded](https://wpewebkit.org/) components including the WPE [WebKit](https://webkit.org/) browser.

## Cloning repository
This repository has git hooks enabled.
The git pre-commit hook, will automatically start the linting process for the openembedded recipes.
To install the hooks please run `linter/install-hooks.sh`
The linter can also be used in standalone mode.

## Quick Links

* Git repository: <https://github.com/WebPlatformForEmbedded/meta-wpe>
* Issues management: <https://github.com/WebPlatformForEmbedded/meta-wpe/issues>
* Documentation: <https://github.com/WebPlatformForEmbedded/meta-wpe/wiki>

## Quick Start

1. `git clone git@github.com:YoeDistro/yoe-distro.git` and `cd yoe-distro`
2. `. raspberrypi3-envsetup.sh` (OR any other machine with `. ./\<machine\>-envsetup.sh`)
3. `yoe_setup`
4. `yoe_add_layer git@github.com:WebPlatformForEmbedded/meta-wpe master`
5. Add the following lines to `conf/local.conf` to build an eglfs image:

    `DISTRO_FEATURES_remove_rpi = "x11"`
    `DISTRO_FEATURES_remove_rpi = "wayland"`
    `MACHINE_FEATURES_remove = "vc4graphics"`

    Note: If you want to build a Wayland based image, don't remove Wayland and you can enable vc4graphics.

6. Build an full screen EGL image: `bitbake wpe-eglfs-image`.
7. Insert SD card
8. lsblk (note sd card device, and substitute for /dev/sdX below)
9. yoe_install_image /dev/sdX wpe-eglfs-image
10. Install SD card in a Raspberry PI and enjoy your new image

Note: If you do not want to use docker to build the image, please set `export DOCKER_REPO=none` prior to running the bitbake step.

Please visit the [documentation](https://github.com/WebPlatformForEmbedded/meta-wpe/wiki) for detailed instructions, including tutorials and HowTo's for different devices for WPE.

For more information on the core Distro please visit [Yoe docs](https://github.com/YoeDistro/yoe-distro/blob/master/docs/README.md)

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
