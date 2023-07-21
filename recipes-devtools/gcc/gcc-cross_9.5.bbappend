# Enable cortex-a53 flag to get support for some of the arm instrcutions set used with cobalt: x25519-asm-arm.S
EXTRA_OECONF_append_arm = " --with-cpu=cortex-a53"
