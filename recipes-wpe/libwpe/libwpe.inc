SUMMARY = "General-purpose library specifically developed for the WPE-flavored port of WebKit."
HOMEPAGE = "https://github.com/WebPlatformForEmbedded/libwpe"
BUGTRACKER = "https://github.com/WebPlatformForEmbedded/libwpe/issues"

DEPENDS = "virtual/egl libxkbcommon"

PROVIDES += "virtual/libwpe"
RPROVIDES_${PN} += "virtual/libwpe"

# Workaround build issue with RPi userland EGL libraries.
CFLAGS_append_rpi = " ${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', '', '-D_GNU_SOURCE', d)}"

inherit cmake
