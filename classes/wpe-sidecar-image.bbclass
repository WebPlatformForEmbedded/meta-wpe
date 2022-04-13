# This is a base image class that eases the creation of sidecar packages.
# In order to use it in your custom image recipe, do the following:
#       1. Inherit this class while having meta-wpe added as a layer.
#           
#           inherit wpe-sidecar-image
#
#       2. Append / override the WPE_SIDECAR_FILENAMES_TO_COPY option, in order
#           to specify which libraries / binaries you'd like to be installed 
#           the target package.
#       
#           WPE_SIDECAR_FILENAMES_TO_COPY:append = " \
#               WPEFramework \
#               WPEProcess \
#            "
# This will produce a selected archive type with an ${IMAGE_NAME} in tmp/deploy/images/.

inherit image

WPE_SIDECAR_FILENAMES_TO_COPY ??= ""

do_makewpe() {

    for line in ${WPE_SIDECAR_FILENAMES_TO_COPY}; do
	    find "${IMAGE_ROOTFS}" -name "$line*" -printf "%P\n" >> "${IMAGE_ROOTFS}/absolute_filepath_list.txt"
    done

    mkdir -p "${IMAGE_ROOTFS}/.temp_sysroot"
    rsync -ar --files-from="${IMAGE_ROOTFS}"/absolute_filepath_list.txt "${IMAGE_ROOTFS}" "${IMAGE_ROOTFS}/.temp_sysroot"
    rsync -ar --delete "${IMAGE_ROOTFS}/.temp_sysroot/" "${IMAGE_ROOTFS}"
    rm -rf "${IMAGE_ROOTFS}/.temp_sysroot"
}

addtask makewpe before do_image after do_rootfs
