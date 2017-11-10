 #!/bin/bash
echo "Creating tmp dir"
cd ..
mkdir ./tmp
cd tmp

echo "Fetching buildroot make files"
#wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/icudata/icudata.mk
#wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/libprovision/libprovision.mk
wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/netflix/netflix.mk
wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/opencdm/opencdm.mk
wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/opencdmi/opencdmi.mk
wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/webdriver/webdriver.mk
wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/westeros/westeros.mk
wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/wpe/wpebackend-rdk/wpebackend-rdk.mk
wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/wpe/wpebackend/wpebackend.mk
wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/wpe/wpeframework/wpeframework.mk
wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/wpe/wpeframework-plugins/wpeframework-plugins.mk
wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/wpe/wpeframework-dialserver/wpeframework-dialserver.mk
wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/wpe/wpeframework-netflix/wpeframework-netflix.mk
wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/wpe/wpeframework-spotify/wpeframework-spotify.mk
wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/wpe/wpeframework-ui/wpeframework-ui.mk
wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/wpe/wpelauncher/wpelauncher.mk
#wget -q https://github.com/WebPlatformForEmbedded/buildroot/raw/master/package/wpe/wpewebkit/wpewebkit.mk

cd ..

echo "Checking and updating revs"
#python ./tools/check_srcrevs.py -i ./tmp/icudata.mk 				-o ./recipes-support/icu/icu_57%.bbappend
#python ./tools/check_srcrevs.py -i ./tmp/libprovision.mk 			-o ./recipes-metrological/libprovision/libprovision_2.0.bb
python ./tools/check_srcrevs.py -i ./tmp/netflix.mk 				-o ./recipes-netflix/netflix/netflix_git.bb
python ./tools/check_srcrevs.py -i ./tmp/opencdm.mk 				-o ./recipes-drm/opencdm/opencdm_git.bb
python ./tools/check_srcrevs.py -i ./tmp/opencdmi.mk 				-o ./recipes-drm/opencdmi/opencdmi_git.bb
python ./tools/check_srcrevs.py -i ./tmp/webdriver.mk 				-o ./recipes-metrological/webdriver/webdriver-wpe_git.bb
python ./tools/check_srcrevs.py -i ./tmp/westeros.mk 				-o ./recipes-graphics/westeros/westeros.inc
python ./tools/check_srcrevs.py -i ./tmp/wpebackend-rdk.mk 			-o ./recipes-wpe/wpebackend-rdk/wpebackend-rdk_0.1.bb
python ./tools/check_srcrevs.py -i ./tmp/wpebackend.mk 				-o ./recipes-wpe/wpebackend/wpebackend_0.1.bb
python ./tools/check_srcrevs.py -i ./tmp/wpeframework.mk 			-o ./recipes-wpe/wpeframework/wpeframework_git.bb
python ./tools/check_srcrevs.py -i ./tmp/wpeframework-plugins.mk 		-o ./recipes-wpe/wpeframework/wpeframework-plugins_git.bb
python ./tools/check_srcrevs.py -i ./tmp/wpeframework-dialserver.mk 		-o ./recipes-wpe/wpeframework/wpeframework-dialserver_git.bb
python ./tools/check_srcrevs.py -i ./tmp/wpeframework-netflix.mk 		-o ./recipes-wpe/wpeframework/wpeframework-netflix_git.bb
python ./tools/check_srcrevs.py -i ./tmp/wpeframework-spotify.mk 		-o ./recipes-wpe/wpeframework/wpeframework-spotify_git.bb
python ./tools/check_srcrevs.py -i ./tmp/wpeframework-ui.mk 	                -o ./recipes-wpe/wpeframework/wpeframework-ui_git.bb
python ./tools/check_srcrevs.py -i ./tmp/wpelauncher.mk 			-o ./recipes-wpe/wpelauncher/wpelauncher_0.1.bb
#python ./tools/check_srcrevs.py -i ./tmp/wpewebkit.mk 				-o ./recipes-wpe/wpewebkit/wpewebkit_0.1.bb


echo "Cleaning up tmp"
rm -r ./tmp

echo "Done."
