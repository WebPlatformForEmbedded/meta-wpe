ROOTFS_POSTPROCESS_COMMAND_append = " update_thunder_path_permissions;"
update_thunder_path_permissions () {
    if [ "${WPEFRAMEWORK_GROUP}" != "" ] && [ "${WPEFRAMEWORK_SUPER_USER_GROUP}" != "" ]; then
        if [ -d "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}" ]; then
            chmod 0550 "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}"
            chown "${WPEFRAMEWORK_SUPER_USER_GROUP}:${WPEFRAMEWORK_GROUP}" "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}"
        fi
	if [ -d "${IMAGE_ROOTFS}/${WPEFRAMEWORK_PERSISTENT_PATH}" ]; then
            chmod 0755 "${IMAGE_ROOTFS}/${WPEFRAMEWORK_PERSISTENT_PATH}"
            chown "${WPEFRAMEWORK_SUPER_USER_GROUP}:${WPEFRAMEWORK_GROUP}" "${IMAGE_ROOTFS}/${WPEFRAMEWORK_PERSISTENT_PATH}"
        fi
	if [ -d "${IMAGE_ROOTFS}/${WPEFRAMEWORK_PERSISTENT_PATH}/WPEFramework" ]; then
            chmod 0770 "${IMAGE_ROOTFS}/${WPEFRAMEWORK_PERSISTENT_PATH}/WPEFramework"
            chown "${WPEFRAMEWORK_SUPER_USER_GROUP}:${WPEFRAMEWORK_SUPER_USER_GROUP}" "${IMAGE_ROOTFS}/${WPEFRAMEWORK_PERSISTENT_PATH}/WPEFramework"
        fi

    fi
}

ROOTFS_POSTPROCESS_COMMAND_append = " update_ocdm_data_path_permissions;"
update_ocdm_data_path_permissions () {
    if [ "${WPEFRAMEWORK_OCDM_GROUP}" != "" ] && [ "${WPEFRAMEWORK_OCDM_SUPER_USER_GROUP}" != "" ]; then
        if [ -d "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/OCDM" ]; then
            chmod 0550 "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/OCDM"
            chown -R "${WPEFRAMEWORK_OCDM_SUPER_USER_GROUP}:${WPEFRAMEWORK_OCDM_GROUP}" "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/OCDM"
        fi
    fi
}

ROOTFS_POSTPROCESS_COMMAND_append = " update_cobalt_data_path_permissions;"
update_cobalt_data_path_permissions () {
    if [ "${WPE_COBALT_USER}" != "" ] && [ "${WPE_COBALT_SUPER_USER_GROUP}" != "" ]; then
        if [ -d "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/Cobalt" ]; then
            chmod -R 0550 "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/Cobalt"
            chown -R "${WPE_COBALT_USER}:${WPE_COBALT_SUPER_USER_GROUP}" "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/Cobalt"
        fi
    fi
}

ROOTFS_POSTPROCESS_COMMAND_append = " update_webkit_data_path_permissions;"
update_webkit_data_path_permissions () {
    if [ "${WPE_WEBKITBROWSER_USER}" != "" ] && [ "${WPE_WEBKITBROWSER_SUPER_USER_GROUP}" != "" ]; then
        if [ -d "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/WebKitBrowser" ]; then
            chmod -R 0440 "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/WebKitBrowser"
            chown -R "${WPE_WEBKITBROWSER_USER}:${WPE_WEBKITBROWSER_SUPER_USER_GROUP}" "${IMAGE_ROOTFS}/${WPEFRAMEWORK_DATA_PATH}/WebKitBrowser"
        fi
    fi
}
