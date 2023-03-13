include pxcore-libnode.inc

SRC_URI += " \
    file://node-v10.15.3_mods.patch \
    file://openssl_1.0.2_compatibility.patch \
    file://node-v10.15.3_qemu_wrapper.patch \
"

NODE_LIB_VERSION = "64"
PV = "10.15.3"

