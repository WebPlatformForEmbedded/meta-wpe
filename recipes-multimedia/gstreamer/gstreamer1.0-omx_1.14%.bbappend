FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-omx-1.14:"

SRC_URI += " \
	file://0003-omxvideodec-fix-deadlock-on-downstream-EOS.patch \
	file://0004-Enable-video-decoder-when-the-output-buffer-pool-is-.patch \
	file://0005-Manual-revert-of-0603e44-omxvideodec-enc-delay-alloc.patch \
	file://0006-Manual-revert-of-64f7f78-omxvideodec-Fix-segment-see.patch \
"

