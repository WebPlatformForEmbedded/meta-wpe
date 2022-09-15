ROOTFS_POSTPROCESS_COMMAND_append = " update_webkit_data_path_permissions; "
update_webkit_data_path_permissions () {
    if [ -d "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/WebKitBrowser" ]; then
        chmod -R 0440 "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/WebKitBrowser"
        chown -R "${WPE_WEBKITBROWSER_USER}:root" "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/WebKitBrowser"
    fi
}

