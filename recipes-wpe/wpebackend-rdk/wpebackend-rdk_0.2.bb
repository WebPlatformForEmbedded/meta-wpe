require wpebackend-rdk.inc

PV = "0.2"
SRCREV = "2b33f5874fc88e5a3bf1ffe7f5b82a99735e90c4"

SRC_URI += "\
    file://0001-wpeframework-set-display-gsource-priority-to-default.patch \
    file://0001-cmake-also-find-legacy-libwpe.patch \ 
    file://0001-wpeframework-add-AdjustedDimensions.patch \
"