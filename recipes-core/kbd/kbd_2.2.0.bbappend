# Reset compiler -Werror=format-security settings, since netflix library itself want to
# compile with -Wno-format flag in cross gcc with '--with-cpu=cortex-a53' enabled
SECURITY_STRINGFORMAT = ""
