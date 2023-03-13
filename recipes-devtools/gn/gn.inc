HOMEPAGE = "https://cobalt.googlesource.com/third_party/gn/"
SUMMARY = "GN is a meta-build system that generates build files for Ninja."
DESCRIPTION = "GN is currently used as the build system for Chromium, Fuchsia, and related projects."

LICENSE = "Copyright 2015 The Chromium Authors. All rights reserved."
LIC_FILES_CHKSUM = "file://LICENSE;md5=0fca02217a5d49a14dfe2d11837bb34d"

DEPENDS_append = " \
    python3-native \
    ninja-native \
"
RECIPE_BRANCH ?= "main"
SRC_URI = " \
    git://git@cobalt.googlesource.com/third_party/gn.git;protocol=https;branch=${RECIPE_BRANCH} \
    file://0001-fix-for-gn.patch \
"
S = "${WORKDIR}/git"
SRC_URI[sha256sum] = "8ca75a6f8e9b6de5fda3451c30eb622e712af1a692aa728dea5f50c52b90c5cf"
SRCREV ??= "df4a8e68510787e17f87cea99f6a8fe6227fd188"

do_compile() {
    "${STAGING_BINDIR_NATIVE}"/python3-native/python3 ${S}/build/gen.py --no-last-commit-position
    "${STAGING_BINDIR_NATIVE}"/ninja -C ${S}/out
}

do_install() {
    install -d "${D}${bindir}"
    cp "${S}"/out/gn "${D}${bindir}"
}

inherit python3native

