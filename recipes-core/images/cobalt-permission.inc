ROOTFS_POSTPROCESS_COMMAND_append = "update_cobalt_data_path_permissions; "
update_cobalt_data_path_permissions () {
    if [ -d "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/Cobalt" ]; then
        chmod -R 0550 "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/Cobalt"
        chown -R "${WPE_COBALT_USER}:root" "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/Cobalt"
    fi
}

