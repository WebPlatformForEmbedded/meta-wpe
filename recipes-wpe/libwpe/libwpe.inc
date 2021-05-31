SUMMARY = "General-purpose library specifically developed for the WPE-flavored port of WebKit."
HOMEPAGE = "https://github.com/WebPlatformForEmbedded/libwpe"
BUGTRACKER = "https://github.com/WebPlatformForEmbedded/libwpe/issues"

DEPENDS = "virtual/egl libxkbcommon"

PROVIDES += "virtual/libwpe"
RPROVIDES_${PN} += "virtual/libwpe"

inherit cmake

