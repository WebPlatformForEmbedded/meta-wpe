inherit useradd

USERADD_PACKAGES = "${PN}"

GROUPADD_PARAM_${PN} = "-r ${WPEFRAMEWORK_GROUP};"

WPEFRAMEWORK_OCDM_GROUP_PARAM = "${@oe.utils.conditional('WPEFRAMEWORK_OCDM_GROUP', '', '', '-r', d)}"
GROUPADD_PARAM_${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', '${WPEFRAMEWORK_OCDM_GROUP_PARAM} ${WPEFRAMEWORK_OCDM_GROUP}', '', d)}"
