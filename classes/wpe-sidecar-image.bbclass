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
#           WPE_SIDECAR_FILENAMES_TO_COPY_append = " \
#               WPEFramework \
#               WPEProcess \
#            "
# This will produce a selected archive type with an ${IMAGE_NAME} in tmp/deploy/images/.

inherit image

SUMMARY = "Base class for images built as a sidecar solution (archive with selected libraries)."
SECTION = "wpe"
LICENSE = "Apache-2.0"

WPE_SIDECAR_FILENAMES_TO_COPY ??= ""

do_makewpe() {
    echo "${WPE_SIDECAR_FILENAMES_TO_COPY}" >> "${IMAGE_ROOTFS}/temp_filename_list.txt"

    xargs -n1 < "${IMAGE_ROOTFS}/temp_filename_list.txt" > "${IMAGE_ROOTFS}/filename_list.txt" 

    while read line
    do
	    find "${IMAGE_ROOTFS}" -name "$line*" -printf "%P\n" >> "${IMAGE_ROOTFS}/absolute_filepath_list.txt"
    done < "${IMAGE_ROOTFS}"/filename_list.txt

    mkdir -p "${IMAGE_ROOTFS}/.temp_sysroot"
    rsync -ar --files-from="${IMAGE_ROOTFS}"/absolute_filepath_list.txt "${IMAGE_ROOTFS}" "${IMAGE_ROOTFS}/.temp_sysroot"
    rsync -ar "${IMAGE_ROOTFS}/.temp_sysroot/" "${IMAGE_ROOTFS}" --delete
    rm -rf "${IMAGE_ROOTFS}/.temp_sysroot"
}

addtask makewpe before do_image after do_rootfs
