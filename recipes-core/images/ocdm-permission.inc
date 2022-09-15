ROOTFS_POSTPROCESS_COMMAND_append = "update_ocdm_data_path_permissions; "
update_ocdm_data_path_permissions () {
    if [ -d "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/OCDM" ]; then
        chmod 0550 "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/OCDM"
        chown -R "root:${WPEFRAMEWORK_OCDM_GROUP}" "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/OCDM"
    fi
}

