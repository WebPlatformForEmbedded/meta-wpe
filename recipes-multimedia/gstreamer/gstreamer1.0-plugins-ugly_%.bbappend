
# Remove unnecessary decoder etc plugins enabled by default
# Most depend on libraries which require adding to LICENSE_FLAGS_ACCEPTED, so
# (since the plugins are not required) it's easier to just avoid building them.

PACKAGECONFIG:remove = "a52dec lame mad mpeg2dec"

