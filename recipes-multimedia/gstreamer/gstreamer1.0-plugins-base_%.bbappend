# Poky does not seem to have a proper enable opengl flag, instead they incorrectly use --enable-opengl
PACKAGECONFIG[gl] = "--enable-gl,,"
PACKAGECONFIG_append = " gl opus"
