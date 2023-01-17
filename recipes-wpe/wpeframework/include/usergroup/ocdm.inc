inherit useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "-r ${WPEFRAMEWORK_OCDM_GROUP}"

USERADD_PARAM_${PN} = "--system --no-create-home --shell /bin/false"
USERADD_PARAM_${PN} += "-g ${WPEFRAMEWORK_OCDM_GROUP}"
USERADD_PARAM_${PN} += "-G root,${WPEFRAMEWORK_GROUP}"
USERADD_PARAM_${PN} += "${WPE_OPENCDMI_USER}"

