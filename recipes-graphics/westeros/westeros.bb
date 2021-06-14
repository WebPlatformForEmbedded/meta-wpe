require westeros.inc

SUMMARY = "This receipe compiles the westeros compositor component"

SRC_URI_append = "\
    file://0001-Use-intptr_t-to-avoid-precision-errors-on-aarch64.patch \
    file://0002-Add_VCX_flags_support.patch \
    file://0003-Set-default-resolution-to-1080.patch \
"

PACKAGECONFIG ??= "incapp inctest increndergl incsbprotocol xdgv5"

PACKAGECONFIG_append = "${@bb.utils.contains("DISTRO_FEATURES", "x11", " x11", "", d)}"
# as far as only drm resolution module is available so, enabled only for DRM
PACKAGECONFIG_append = " ${@bb.utils.contains('WESTEROS_BACKEND', 'westeros-soc-drm', 'modules', '', d)}"

PACKAGECONFIG[incapp] = "--enable-app=yes"
PACKAGECONFIG[inctest] = "--enable-test=yes"
PACKAGECONFIG[incplayer] = "--enable-player=yes"
PACKAGECONFIG[increndergl] = "--enable-rendergl=yes"
PACKAGECONFIG[incsbprotocol] = "--enable-sbprotocol=yes"
PACKAGECONFIG[xdgv4] = "--enable-xdgv4=yes"
PACKAGECONFIG[xdgv5] = "--enable-xdgv5=yes"
PACKAGECONFIG[x11] = ",,freeglut"
PACKAGECONFIG[modules] = "--enable-modules=yes"

S = "${WORKDIR}/git"

WESTEROS_BACKEND ??= "westeros-soc-drm"

DEPENDS_append = "\
    westeros-simplebuffer \
    westeros-simpleshell \
    ${WESTEROS_BACKEND} \
"

RDEPENDS_${PN} = "xkeyboard-config"

inherit autotools pkgconfig

SECURITY_CFLAGS_remove = "-fpie"
SECURITY_CFLAGS_remove = "-pie"

do_compile_prepend() {
   export SCANNER_TOOL=${STAGING_BINDIR_NATIVE}/wayland-scanner
   oe_runmake -C ${S}/protocol
}

def is_drm_enabled(d):
    return bb.utils.contains('WESTEROS_BACKEND', 'westeros-soc-drm', '1', '0', d)

do_install_append () {
   install -D -m 0644 ${S}/systemd/westeros-env ${D}${sysconfdir}/default/westeros-env
   install -D -m 0755 ${S}/systemd/westeros-init ${D}${bindir}/westeros-init
   if [ -f ${D}${bindir}/westeros-init -a ${@is_drm_enabled(d)} -eq 1 ]; then
       sed -i -e '/--renderer/i export WESTEROS_DRM_MODE=1920x1080' ${D}${bindir}/westeros-init
       sed -i -e '/--renderer/i export LD_PRELOAD=/usr/lib/libwesteros_gl.so.0' ${D}${bindir}/westeros-init
       sed -i -e '/--renderer/ s#$# --module /usr/lib/libresolutionmodule.so#' ${D}${bindir}/westeros-init
   fi
}

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"
INSANE_SKIP_${PN} += "dev-so"

