require include/wpeframework-ocdm-playready.inc

RDEPENDS_${PN} += " playready"

SRCBRANCH = "master"
SRCREV = "7b14ebbe16717d27737e913c6120f848f6663d30"

TARGET_CFLAGS += " -fpermissive"

