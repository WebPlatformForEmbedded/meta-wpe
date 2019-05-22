include pxcore-libnode.inc

SRC_URI += " \
           file://0001-node-v8.15.1_mods.patch \
"

NODE_LIB_VERSION = "57"
PV = "8.15.1"

DEFAULT_PREFERENCE = "-1"