# enable HTTP2 support in curl for AVS Device SDK
DEPENDS_class-target_append = " nghttp2"

PACKAGECONFIG_append = " nghttp2"
PACKAGECONFIG[nghttp2] = "--with-nghttp2,--without-nghttp2"

