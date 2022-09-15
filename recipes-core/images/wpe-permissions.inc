require ${@oe.utils.conditional('WPEFRAMEWORK_GROUP', '', '', 'thunder-permission.inc', d)}
require ${@oe.utils.conditional('WPEFRAMEWORK_OCDM_GROUP', '', '', 'ocdm-permission.inc', d)}

require ${@oe.utils.conditional('WPE_WEBKITBROWSER_USER', '', '', 'webkitbrowser-permission.inc', d)}
require ${@oe.utils.conditional('WPE_COBALT_USER', '', '', 'cobalt-permission.inc', d)}

