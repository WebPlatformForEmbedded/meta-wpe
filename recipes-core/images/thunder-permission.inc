ROOTFS_POSTPROCESS_COMMAND_append = " update_thunder_data_path_permissions;"
update_thunder_data_path_permissions () {
    if [ -d "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}" ]; then
       chmod 0550 "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}"
       chown "root:${WPEFRAMEWORK_GROUP}" "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}"
    fi
}

