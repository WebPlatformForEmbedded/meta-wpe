# Enable extra options for rpi. Fixme, needs review.

PACKAGECONFIG_append = " matroska"

PACKAGECONFIG[matroska] = "--enable-matroska,--disable-matroska,"
