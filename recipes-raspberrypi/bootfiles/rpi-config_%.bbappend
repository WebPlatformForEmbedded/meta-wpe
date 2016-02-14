HDMI_GROUP ?= "1"
# for 720p use 4 (optimum) and for 1080p use 16
HDMI_MODE ?= "4"

do_deploy_append() {
	# display settings
	sed -i '/#hdmi_group/ c\hdmi_group=${HDMI_GROUP}' ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	sed -i '/#hdmi_mode/ c\hdmi_mode=${HDMI_MODE}' ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
}
