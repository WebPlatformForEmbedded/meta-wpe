# Work around to avoid mismatch between precompiled libprovision against openssl with standard sonames (I dunno they do this anyway)
SRC_URI_remove = "file://debian1.0.2/soname.patch"
