ROOTFS_POSTPROCESS_COMMAND += "update_webkit_data_path_permissions"
update_webkit_data_path_permissions () {
    chmod -R 0440 "${D}/${WPEFRAMEWORK_DATA_PATH}/WebKitBrowser"
    chown -R "${WPE_WEBKITBROWSER_GROUP}:root" "${D}/${WPEFRAMEWORK_DATA_PATH}/WebKitBrowser"
}

