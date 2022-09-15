ROOTFS_POSTPROCESS_COMMAND += "update_ocdm_data_path_permissions"
update_ocdm_data_path_permissions () {
    chmod 0550 "${D}/${WPEFRAMEWORK_DATA_PATH}/OCDM"
}

