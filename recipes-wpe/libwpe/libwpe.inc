SUMMARY = "General-purpose library for WPEWebKit."
DESCRIPTION = "General-purpose library specifically developed for the WPE-flavored port of WebKit."
HOMEPAGE = "https://github.com/WebPlatformForEmbedded/libwpe"
BUGTRACKER = "https://github.com/WebPlatformForEmbedded/libwpe/issues"

DEPENDS:append = " libxkbcommon virtual/egl"

PROVIDES += "virtual/libwpe"

inherit cmake

RPROVIDES:${PN} += "virtual/libwpe"
